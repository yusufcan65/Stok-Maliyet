package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Enum.EntrySourceType;
import com.inonu.stok_takip.Exception.MaterialEntry.MaterialEntryNotFoundException;
import com.inonu.stok_takip.Repositoriy.MaterialEntryRepository;
import com.inonu.stok_takip.Service.*;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryUpdateRequest;
import com.inonu.stok_takip.dto.Response.MaterialEntryResponse;
import com.inonu.stok_takip.entitiy.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialEntryServiceImpl implements MaterialEntryService {

    private final MaterialEntryRepository materialEntryRepository;
    private final PurchaseTypeService purchaseTypeService;
    private final ProductService productService;
    private final PurchasedUnitService purchasedUnitService;
    private final BudgetService budgetService;
    private final TenderService tenderService;

    public MaterialEntryServiceImpl(MaterialEntryRepository materialEntryRepository,
                                    PurchaseTypeService purchaseTypeService,
                                    ProductService productService,
                                    PurchasedUnitService purchasedUnitService,
                                    BudgetService budgetService, TenderService tenderService) {
        this.materialEntryRepository = materialEntryRepository;
        this.purchaseTypeService = purchaseTypeService;
        this.productService = productService;
        this.purchasedUnitService = purchasedUnitService;
        this.budgetService = budgetService;
        this.tenderService = tenderService;
    }

    @Override
    public List<MaterialEntryResponse> getAllMaterialEntryList() {
        List<MaterialEntry> materialEntryList = materialEntryRepository.findAll();
        return mapToResponseList(materialEntryList);
    }

    @Override
    public MaterialEntryResponse createMaterialEntry(MaterialEntryCreateRequest request) {

        Product product = productService.getProductById(request.productId());
        PurchasedUnit purchasedUnit = purchasedUnitService.getPurchasedUnitById(request.purchaseUnitId());
        PurchaseType purchaseType = purchaseTypeService.getPurchaseTypeById(request.purchaseTypeId());
        Budget budget = budgetService.getBudgetById(request.budgetId());


        Double totalPrice = request.unitPrice() * request.quantity(); // ürünün toplam fiyatı kdv dahil değil
        Double totalVat = totalPrice * product.getVatAmount(); // toplam fiyat üzeinden toplm kdv tutarı
        Double totalPriceIncludingVat = totalPrice + totalVat; // kdv eklenmiş hali ile toplam tutar

        MaterialEntry materialEntry = mapToEntity(request);

        if (request.tenderId() != null) { // ihale için tender id değeri seçilen tenderden gelecek şekilde burada kontrol ediliyor
            Tender tender = tenderService.getTenderById(request.tenderId());
            materialEntry.setTender(tender);
        } else {
            materialEntry.setTender(null); // doğrudan alımda tender yoksa NULL atanır
        }


        materialEntry.setTotalPrice(totalPrice);
        materialEntry.setRemainingQuantity(request.quantity());
        materialEntry.setTotalPriceIncludingVat(totalPriceIncludingVat);
        materialEntry.setEntrySourceType(request.entrySourceType());
        materialEntry.setProduct(product);
        materialEntry.setPurchaseType(purchaseType);
        materialEntry.setPurchasedUnit(purchasedUnit);

        budgetService.updateBudgetValue(budget.getId(),totalPriceIncludingVat);

        materialEntry.setBudget(budget);


        MaterialEntry toSave = materialEntryRepository.save(materialEntry);
        return mapToResponse(toSave);

    }

    @Override
    public MaterialEntryResponse updateMaterialEntry(MaterialEntryUpdateRequest request) {
        MaterialEntry materialEntry = getMaterialEntryById(request.id());

        materialEntry.setQuantity(request.quantity());
        materialEntry.setRemainingQuantity(request.quantity());

        MaterialEntry saved = materialEntryRepository.save(materialEntry);
        return mapToResponse(saved);
    }

    @Override
    public Double getTotalRemainingQuantity(Long productId) {
        return materialEntryRepository.sumRemainingQuantityByProductId(productId);
    }


    @Override
    public MaterialEntry getMaterialEntryById(Long id) {
        return materialEntryRepository.findById(id).orElseThrow(()-> new MaterialEntryNotFoundException("Material Entry Not Found"));
    }

    @Override
    public MaterialEntryResponse deleteMaterialEntry(Long id) {

        MaterialEntry materialEntry = getMaterialEntryById(id);
        materialEntryRepository.delete(materialEntry);
        return mapToResponse(materialEntry);

    }

    // bu metot bir üründen stokta toplamda ne kadar var kaç kalem var onu getiriyor
    @Override
    public List<MaterialEntry> getMaterialEntryByProductId(Long productId) {
        List<MaterialEntry> materialEntries = materialEntryRepository.findByProductIdOrderByEntryDateAsc(productId);
        return materialEntries;
    }

    // depodan ürün çıkışı olduktan sonra depodaki kalan ürün miktarını günceller
    @Override
    public Double updateRemainingQuantity(Long materialEntryId, Double exitQuantity) {
        MaterialEntry materialEntry = getMaterialEntryById(materialEntryId);
        Double remainingQuantity = materialEntry.getRemainingQuantity();
        Double newValue = remainingQuantity - exitQuantity;
        materialEntry.setRemainingQuantity(newValue);
        materialEntryRepository.save(materialEntry);
        return newValue;
    }

    // devir işlemerini yapan metot
    @Override
    public List<MaterialEntryResponse> carryOverEntriesToNextYear(DateRequest request) {
        // Dönem içinde kalan malzemeleri al
        List<MaterialEntry> entriesToCarryOver = materialEntryRepository.findEntriesWithinPeriod(request.startDate(), request.endDate());

        List<MaterialEntry> materialEntryList = new ArrayList<>();

        for (MaterialEntry oldEntry : entriesToCarryOver) {
            if (oldEntry.getRemainingQuantity() > 0) { // Sadece kalan miktarı olanları devret
                MaterialEntry newEntry = new MaterialEntry();

                Double totalPrice = oldEntry.getUnitPrice() * oldEntry.getRemainingQuantity();

                newEntry.setProduct(oldEntry.getProduct());
                newEntry.setQuantity(oldEntry.getRemainingQuantity());
                newEntry.setRemainingQuantity(oldEntry.getRemainingQuantity());
                newEntry.setExpiryDate(oldEntry.getExpiryDate());
                newEntry.setEntryDate(LocalDate.now());
                newEntry.setBudget(oldEntry.getBudget());
                newEntry.setCompanyName(oldEntry.getCompanyName());
                newEntry.setEntrySourceType(EntrySourceType.DEVIR);
                newEntry.setTotalPriceIncludingVat(oldEntry.getTotalPriceIncludingVat());
                newEntry.setDescription(oldEntry.getDescription());
                newEntry.setPurchaseType(oldEntry.getPurchaseType());
                newEntry.setPurchasedUnit(oldEntry.getPurchasedUnit());

                newEntry.setUnitPrice(oldEntry.getUnitPrice());
                newEntry.setTotalPrice(totalPrice);
                materialEntryList.add(newEntry);
                oldEntry.setRemainingQuantity(0.0);

                materialEntryRepository.save(oldEntry);
                materialEntryRepository.save(newEntry);
            }
        }
        return mapToResponseList(materialEntryList);
    }

    private MaterialEntry mapToEntity(MaterialEntryCreateRequest request) {
        MaterialEntry materialEntry = new MaterialEntry();
        materialEntry.setQuantity(request.quantity());
        materialEntry.setUnitPrice(request.unitPrice());
        materialEntry.setDescription(request.Description());
        materialEntry.setCompanyName(request.companyName());
        materialEntry.setExpiryDate(request.expiryDate());
        materialEntry.setEntryDate(request.entryDate());
        return materialEntry;
    }

    private MaterialEntryResponse mapToResponse(MaterialEntry materialEntry) {
        MaterialEntryResponse materialEntryResponse = new MaterialEntryResponse(
                materialEntry.getQuantity(),
                materialEntry.getUnitPrice(),
                materialEntry.getEntryDate(),
                materialEntry.getExpiryDate(),
                materialEntry.getCompanyName(),
                materialEntry.getDescription(),
                materialEntry.getTotalPrice(),
                materialEntry.getTotalPriceIncludingVat(),
                materialEntry.getProduct().getId(),
                materialEntry.getPurchaseType().getId(),
                materialEntry.getPurchasedUnit().getId(),
                materialEntry.getEntrySourceType()

        );
        return materialEntryResponse;
    }

    private List<MaterialEntryResponse> mapToResponseList(List<MaterialEntry> materialEntryList) {
        List<MaterialEntryResponse> materialEntryResponseList = materialEntryList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return materialEntryResponseList;
    }




}
