package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Exception.MaterialEntry.StockNotAvailableException;
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
    private final PurchaseFormService purchaseFormService;
    private final BudgetService budgetService;

    public MaterialEntryServiceImpl(MaterialEntryRepository materialEntryRepository,
                                    PurchaseTypeService purchaseTypeService,
                                    ProductService productService,
                                    PurchasedUnitService purchasedUnitService,
                                    PurchaseFormService purchaseFormService,
                                    BudgetService budgetService) {
        this.materialEntryRepository = materialEntryRepository;
        this.purchaseTypeService = purchaseTypeService;
        this.productService = productService;
        this.purchasedUnitService = purchasedUnitService;
        this.purchaseFormService = purchaseFormService;
        this.budgetService = budgetService;
    }

    @Override
    public List<MaterialEntryResponse> getAllMaterialEntryList() {
        List<MaterialEntry> materialEntryList = materialEntryRepository.findAll();
        return mapToResponseList(materialEntryList);
    }

    @Override
    public MaterialEntryResponse createMaterialEntry(MaterialEntryCreateRequest request) {

        Product product = productService.getProductById(request.productId());
        PurchaseForm purchaseForm = purchaseFormService.getPurchaseFormNameById(request.purchaseFormId());
        PurchasedUnit purchasedUnit = purchasedUnitService.getPurchasedUnitById(request.purchaseUnitId());
        PurchaseType purchaseType = purchaseTypeService.getPurchaseTypeById(request.purchaseTypeId());
        Budget budget = budgetService.getBudgetById(request.budgetId());

        Double totalPrice = request.unitPrice() * request.quantity(); // ürnün toplam fiyatı kdv dahil değil
        Double totalVat = totalPrice * product.getVatAmount(); // toplam fiyat üzeinden toplm kdv tutarı
        Double totalPriceIncludingVat = totalPrice + totalVat; // kdv eklenmiş hali ile toplam tutar

        MaterialEntry materialEntry = mapToEntity(request);
        materialEntry.setTotalPrice(totalPrice);
       // materialEntry.setRemainingQuantity(request.quantity());
       // materialEntry.setRemainingQuantityInTender(request.quantity());
        materialEntry.setTotalPriceIncludingVat(totalPriceIncludingVat);
        materialEntry.setEntrySourceType(EntrySourceType.ALIM);
        materialEntry.setProduct(product);
        materialEntry.setPurchaseForm(purchaseForm);
        materialEntry.setPurchaseType(purchaseType);
        materialEntry.setPurchasedUnit(purchasedUnit);
        materialEntry.setBudget(budget);
        budgetService.updateBudgetValue(budget.getId(),totalPriceIncludingVat);

        if(purchaseForm.getName().equalsIgnoreCase("Doğrudan Alım")){
            materialEntry.setRemainingQuantityInTender(0.0);
            materialEntry.setRemainingQuantity(request.quantity());

        }
        else {
            materialEntry.setRemainingQuantity(0.0);
            materialEntry.setRemainingQuantityInTender(request.quantity());
        }

        MaterialEntry toSave = materialEntryRepository.save(materialEntry);
        return mapToResponse(toSave);

    }

    @Override
    public MaterialEntryResponse updateMaterialEntry(MaterialEntryUpdateRequest request) {
        return null;
    }

    @Override
    public MaterialEntry getMaterialEntryById(Long id) {
        return materialEntryRepository.findById(id).orElseThrow(()-> new RuntimeException("Material Entry Not Found"));
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
        List<MaterialEntry> materialEntries = materialEntryRepository.findMaterialEntryByProductId(productId);
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

    @Override
    public Double updateRemainingQuantityInTender(Long materialId, Double exitQuantity){
        MaterialEntry materialEntry = getMaterialEntryById(materialId);
        Double remainingQuantityInRender = materialEntry.getRemainingQuantityInTender();
        Double newValue = remainingQuantityInRender - exitQuantity;
        materialEntry.setRemainingQuantityInTender(newValue);
        materialEntryRepository.save(materialEntry);
        return newValue;
    }

    @Override
    public void checkStockAvailabilityByProductAndPurchaseForm(Long productId, Long purchaseFormId, Double requestedQuantity) {
        List<MaterialEntry> materialEntries = getByProductIdAndPurchaseFormIdOrderedByEntryDate(productId, purchaseFormId);

        if (materialEntries.isEmpty()) {
            throw new RuntimeException("Ürün stokta bulunamadı");
        }

        double totalAvailableQuantity = materialEntries.stream()
                .mapToDouble(MaterialEntry::getRemainingQuantityInTender)
                .sum();

        if (totalAvailableQuantity < requestedQuantity) {
            throw new StockNotAvailableException("Stok yetersiz");
        }
    }
    @Override
    public List<MaterialEntry> getByProductIdAndPurchaseFormIdOrderedByEntryDate(Long productId, Long purchaseFormId) {
        return materialEntryRepository.getByProductIdAndPurchaseFormIdOrderedByEntryDate(productId, purchaseFormId);
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
                newEntry.setPurchaseForm(oldEntry.getPurchaseForm());// alım şekli burada devir olacak ona göre işlem yapmamız gerekecek ykarıda bunu hallettik
                                                                   // burada ihaleleri devam eden veriler ihale devir olarak devam edecek burada bakılacak olan ksım ise
                                                                 // ihale bitiş tarihi ve ihaleden alınan üründen kalan miktar olacak ona göre projeyi güncelleyecez
                newEntry.setUnitPrice(oldEntry.getUnitPrice());
                newEntry.setTotalPrice(totalPrice);
                newEntry.setRemainingQuantityInTender(oldEntry.getRemainingQuantityInTender());
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
                materialEntry.getPurchaseForm().getId(),
                materialEntry.getPurchasedUnit().getId()

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
