package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.TenderRepository;
import com.inonu.stok_takip.Service.*;
import com.inonu.stok_takip.dto.Request.TenderCreateRequest;
import com.inonu.stok_takip.dto.Response.ProductDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderProductDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderResponse;
import com.inonu.stok_takip.entitiy.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
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
        tender.setTotalAmount(totalAmount);

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
    public List<TenderResponse> getAllTenders() {
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

    //yıl bittiği için bütün ihaleleri silen kod yapısı  bitmemiş devam ediyor
    @Override
    public void handleTendersAtYearEnd() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        List<Tender> tenders = tenderRepository.findAll();

        for (Tender tender : tenders) {
            boolean isExpired = tender.getEndDate().isBefore(today);
            boolean isYearEnd = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 31;

            if (isExpired || isYearEnd) {
                if (tender.isActive()) {
                    tender.setActive(false);
                    tenderRepository.save(tender);
                }
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
    public TenderResponse increaseTenderByTwentyPercent(Long tenderId) {
        Tender tender = tenderRepository.findById(tenderId)
                .orElseThrow(() -> new RuntimeException("Tender not found"));

        if (tender.isIncreased()) {
            throw new IllegalStateException("Bu ihale zaten %20 artırıldı!");
        }

        // Yüzde 20 artırımı
        tender.setTotalAmount(tender.getTotalAmount() * 1.2);
        if (tender.getTenderQuantity() != null) {
            Double newValue = tender.getTenderQuantity() * 0.2;
            tender.setTenderQuantity(tender.getTenderQuantity() * 1.2);
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

    public List<TenderResponse> getTenderByProductId(){

        return null;
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
                tender.getUnitPrice(),
                tender.getTotalAmount(),
                tender.getCompanyName(),
                tender.getPurchaseForm().getId(),
                tender.getProduct().getId(),
                tender.getPurchasedUnit().getId(),
                tender.getPurchaseType().getId()
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
