package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.MaterialDemandRepository;
import com.inonu.stok_takip.Service.MaterialDemandService;
import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.Service.ProductService;
import com.inonu.stok_takip.Service.PurchaseFormService;
import com.inonu.stok_takip.dto.Request.MaterialDemandCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialDemandResponse;
import com.inonu.stok_takip.entitiy.MaterialDemand;
import com.inonu.stok_takip.entitiy.MaterialEntry;
import com.inonu.stok_takip.entitiy.Product;
import com.inonu.stok_takip.entitiy.PurchaseForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialDemandServiceImpl implements MaterialDemandService {

    private final MaterialDemandRepository materialDemandRepository;
    private final ProductService productService;
    private final MaterialEntryService materialEntryService;
    private final PurchaseFormService purchaseFormService;

    public MaterialDemandServiceImpl(MaterialDemandRepository materialDemandRepository, ProductService productService, MaterialEntryService materialEntryService, PurchaseFormService purchaseFormService) {
        this.materialDemandRepository = materialDemandRepository;
        this.productService = productService;
        this.materialEntryService = materialEntryService;
        this.purchaseFormService = purchaseFormService;
    }

    @Override
    public MaterialDemandResponse createMaterialDemand(MaterialDemandCreateRequest request) {
        // 1. Stok kontrolü yap
        materialEntryService.checkStockAvailabilityByProductAndPurchaseForm(request.productId(),  request.purchaseFormId(), request.quantity());

        // 2. Ürün ve PurchaseForm nesnelerini al
        Product product = productService.getProductById(request.productId());
        PurchaseForm purchaseForm = purchaseFormService.getPurchaseFormById(request.purchaseFormId());

        // 3. MaterialDemand nesnesini oluştur
        MaterialDemand materialDemand = mapToEntity(request);
        materialDemand.setProduct(product);
        materialDemand.setPurchaseForm(purchaseForm);

        // 4. Stoktan miktar düşme işlemi (updateRemainingQuantity)
        List<MaterialEntry> entries = materialEntryService.getByProductIdAndPurchaseFormIdOrderedByEntryDate(request.productId(), request.purchaseFormId());
        double remaining = request.quantity();

        for (MaterialEntry entry : entries) {
            if (remaining <= 0) break;

            double available = entry.getRemainingQuantity();
            if (available <= 0) continue;

            double toDeduct = Math.min(available, remaining);

            // Mevcut metodunu burada kullan
            materialEntryService.updateRemainingQuantity(entry.getId(), toDeduct);

            remaining -= toDeduct;
        }

        if (remaining > 0) {
            throw new RuntimeException("Beklenmedik hata: Tüm miktar stoktan düşülemedi.");
        }

        // 5. Kaydet ve response döndür
        MaterialDemand savedDemand = materialDemandRepository.save(materialDemand);
        return mapToResponse(savedDemand);
    }



    @Override
    public MaterialDemand getMaterialDemandById(Long id) {
        return materialDemandRepository.findById(id).orElseThrow(()->new RuntimeException("Material demand not found"));
    }

    @Override
    public List<MaterialDemandResponse> getAllMaterialDemand() {
        List<MaterialDemand> materialDemandList = materialDemandRepository.findAll();
        return mapToResponseList(materialDemandList);
    }

    @Override
    public MaterialDemandResponse updateMaterialDemand(MaterialDemandCreateRequest request) {
        return null;
    }

    @Override
    public MaterialDemandResponse deleteMaterialDemand(Long id) {
        return null;
    }
    private MaterialDemandResponse mapToResponse(MaterialDemand materialDemand) {
        MaterialDemandResponse materialDemandResponse = new MaterialDemandResponse();
        materialDemandResponse.setId(materialDemand.getId());
        materialDemandResponse.setCompanyName(materialDemand.getCompanyName());
        materialDemandResponse.setUserId(materialDemand.getUserId());
        materialDemandResponse.setQuantity(materialDemand.getQuantity());
        materialDemandResponse.setRequestDate(materialDemand.getRequestDate());
        materialDemandResponse.setProductId(materialDemand.getProduct().getId());

        return materialDemandResponse;
    }

    private List<MaterialDemandResponse> mapToResponseList(List<MaterialDemand> materialDemandList) {
        List<MaterialDemandResponse> materialDemandResponseList = materialDemandList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return materialDemandResponseList;
    }

    private MaterialDemand mapToEntity(MaterialDemandCreateRequest request) {
        MaterialDemand materialDemand = new MaterialDemand();
        materialDemand.setQuantity(request.quantity());
        materialDemand.setRequestDate(request.requestDate());
        materialDemand.setCompanyName(request.companyName());
        materialDemand.setUserId(request.userId());
        return materialDemand;
    }
}
