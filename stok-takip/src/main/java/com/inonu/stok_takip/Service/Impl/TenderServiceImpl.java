package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Exception.Tender.TenderAlreadyIncreasedException;
import com.inonu.stok_takip.Exception.Tender.TenderNotFoundException;
import com.inonu.stok_takip.Repositoriy.TenderRepository;
import com.inonu.stok_takip.Service.*;
import com.inonu.stok_takip.dto.Request.TenderCreateRequest;
import com.inonu.stok_takip.dto.Response.TenderDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderProductDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderResponse;
import com.inonu.stok_takip.entitiy.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderServiceImpl implements TenderService {

    private final TenderRepository tenderRepository;
    private final PurchaseFormService purchaseFormService;
    private final PurchasedUnitService purchasedUnitService;
    private final PurchaseTypeService purchaseTypeService;
    private final ProductService productService;

    public TenderServiceImpl(TenderRepository tenderRepository,
                             PurchaseFormService purchaseFormService,
                             PurchasedUnitService purchasedUnitService,
                             PurchaseTypeService purchaseTypeService,
                             ProductService productService) {
        this.tenderRepository = tenderRepository;
        this.purchaseFormService = purchaseFormService;
        this.purchasedUnitService = purchasedUnitService;
        this.purchaseTypeService = purchaseTypeService;
        this.productService = productService;
    }

    @Override
    public TenderResponse createTender(TenderCreateRequest request) {

        PurchaseForm purchaseForm = purchaseFormService.getPurchaseFormById(request.purchaseFormId());
        PurchaseType purchaseType = purchaseTypeService.getPurchaseTypeById(request.purchaseTypeId());
        PurchasedUnit purchasedUnit = purchasedUnitService.getPurchasedUnitById(request.purchasedUnitId());
        Product product = productService.getProductById(request.productId());

        Tender tender = mapToEntity(request);
        Double totalAmount = tender.getUnitPrice() * tender.getTenderQuantity();
        tender.setTotalAmount(totalAmount);
        tender.setPurchaseForm(purchaseForm);
        tender.setProduct(product);
        tender.setPurchaseType(purchaseType);
        tender.setPurchasedUnit(purchasedUnit);
        tender.setRemainingQuantityInTender(tender.getTenderQuantity());

        Tender savedTender = tenderRepository.save(tender);

        return mapToResponse(savedTender);
    }

    @Override
    public TenderResponse updateTenderRemainingQuantity(Long tenderId, Double quantity){
        Tender tender = getTenderById(tenderId);
        tender.setRemainingQuantityInTender(tender.getRemainingQuantityInTender() - quantity);
        Tender savedTender = tenderRepository.save(tender);
        return mapToResponse(savedTender);
    }

    @Override
    public List<TenderResponse> getAllActiveTenders() {
        List<Tender> tenders = tenderRepository.findTenderByActiveTrue();
        return mapToResponseList(tenders);
    }
    @Override
    public List<TenderResponse> getAllTenders(){
        List<Tender> tenders = tenderRepository.findAll();
        return mapToResponseList(tenders);
    }


    // bu kod ile hangi madde ile ne kadar ürün alınmış ürün durumları nasıl kontrol ediliyor
    @Override
    public List<TenderResponse> getTendersByPurchaseForm(Long purchaseFormId) {

        List<Tender> tenderList = tenderRepository.findByPurchaseFormId(purchaseFormId);

        return mapToResponseList(tenderList);
    }

    @Override
    public Double calculateTotalAmountByPurchaseFormId(Long purchaseFormId) {
        List<TenderResponse> responseList = getTendersByPurchaseForm(purchaseFormId);

        return responseList.stream()
                .filter(tender -> tender.totalAmount() != null)
                .mapToDouble(TenderResponse::totalAmount)
                .sum();
    }

    //yıl bittiği için veya ihale süresi dolmuş bütün ihaleleri silen kod yapısı bu kod her saate bir tetikleniyor
    @Override
    @Scheduled(cron = "0 0 * * * ?")
    public void handleTendersAtYearEnd() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();

        // 31 Aralık kontrolü
        boolean isYearEnd = today.getMonthValue() == 12 && today.getDayOfMonth() == 31;

        List<Tender> tenders = tenderRepository.findAll();

        for (Tender tender : tenders) {
            boolean isExpired = tender.getEndDate().isBefore(today);

            // Eğer ihale süresi dolmuşsa veya yılın son günündeysek
            if ((isExpired || isYearEnd) && tender.isActive()) {
                tender.setActive(false);
                tenderRepository.save(tender);
            }
        }
    }

    @Override
    public List<TenderDetailResponse> getPurchaseFormsWithDetails() {
        return tenderRepository.findAll().stream()
                .collect(Collectors.groupingBy(tender -> tender.getPurchaseForm().getName()))
                .entrySet().stream()
                .map(entry -> {
                    String formName = entry.getKey();
                    List<TenderProductDetailResponse> products = entry.getValue().stream().map(tender -> new TenderProductDetailResponse(

                            tender.getId(),
                            tender.getProduct().getName(),
                            tender.getProduct().getMeasurementType().getName(),
                            tender.getTenderQuantity(),
                            tender.getRemainingQuantityInTender(),
                            tender.isIncreased(),
                            tender.getUnitPrice(),
                            tender.getTotalAmount()
                    )).collect(Collectors.toList());

                    Double totalAmount = products.stream()
                            .mapToDouble(TenderProductDetailResponse::totalAmount)
                            .sum();

                    return new TenderDetailResponse(formName, totalAmount, products);
                }).collect(Collectors.toList());
    }

    //ihaleyi %20 arttıran metot
    @Transactional
    @Override
    public TenderResponse increaseTenderByTwentyPercent(Long tenderId,Double increasedQuantity) {
        Tender tender = tenderRepository.findById(tenderId)
                .orElseThrow(() -> new TenderNotFoundException("Tender not found"));

        if (tender.isIncreased()) {
            throw new TenderAlreadyIncreasedException("Bu ihale zaten artırıldı!");
        }

        // Yüzde 20 artırımı
        Double quantity = (increasedQuantity/100) +1 ;
        Double increased = increasedQuantity/100;

        tender.setTotalAmount(tender.getTotalAmount() * quantity);
        if (tender.getTenderQuantity() != null) {
            Double newValue = tender.getTenderQuantity() * increased;
            tender.setTenderQuantity(tender.getTenderQuantity() * quantity);
            tender.setRemainingQuantityInTender(tender.getRemainingQuantityInTender() + newValue);
        }

        tender.setIncreased(true);
        Tender updatedTender = tenderRepository.save(tender);
        return mapToResponse(updatedTender);
    }

    @Override
    public Tender getTenderById(Long id) {
        return tenderRepository.findById(id).orElseThrow(()-> new RuntimeException("tender not found"));
    }

    @Override
    public List<TenderResponse> getTenderByProductAndCompany(){
        List<Tender> tenders = tenderRepository.findTenderByActiveTrue();
        return mapToResponseList(tenders);
    }


    @Override
    public TenderResponse deleteTender(Long id) {
        return null;
    }

    @Override
    public TenderResponse updateTender(TenderCreateRequest request) {
        return null;
    }

    private TenderResponse mapToResponse(Tender tender) {
        TenderResponse tenderResponse = new TenderResponse(
                tender.getId(),
                tender.getTenderQuantity(),
                tender.getRemainingQuantityInTender(),
                tender.getStartDate(),
                tender.getEndDate(),
                tender.isIncreased(),
                tender.isActive(),
                tender.getUnitPrice(),
                tender.getTotalAmount(),
                tender.getCompanyName(),
                tender.getProduct().getId(),
                tender.getProduct().getName(),
                tender.getProduct().getMeasurementType().getName(),
                tender.getPurchasedUnit().getName(),
                tender.getPurchaseType().getName(),
                tender.getPurchaseForm().getName()
        );
        return tenderResponse;
    }
    private List<TenderResponse> mapToResponseList(List<Tender> tenderList) {
        List<TenderResponse> tenderResponseList = tenderList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return tenderResponseList;
    }
    private Tender mapToEntity(TenderCreateRequest request) {
        Tender tender = new Tender();
        tender.setTenderQuantity(request.tenderQuantity());
        tender.setStartDate(request.startDate());
        tender.setEndDate(request.endDate());
        tender.setUnitPrice(request.unitPrice());
        tender.setCompanyName(request.companyName());
        return tender;
    }

}
