package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.MaterialExitRepository;
import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.Service.MaterialExitService;
import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.entitiy.MaterialEntry;
import com.inonu.stok_takip.entitiy.MaterialExit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialExitServiceImpl implements MaterialExitService {

    private final MaterialExitRepository materialExitRepository;
    private final MaterialEntryService materialEntryService;

    public MaterialExitServiceImpl(MaterialExitRepository materialExitRepository,
                                   MaterialEntryService materialEntryService) {
        this.materialExitRepository = materialExitRepository;
        this.materialEntryService = materialEntryService;
    }

    @Override
    public List<MaterialExitResponse> getAllMaterialExits() {
        List<MaterialExit> materialExits = materialExitRepository.findAll();
        return mapToResponseList(materialExits);
    }


    @Override
    public MaterialExitResponse createMaterialExit(MaterialExitCreateRequest request) {

        List<MaterialEntry> materialEntries = materialEntryService.getMaterialEntryByProductId(request.productId());

        if(materialEntries.isEmpty()){
            throw new RuntimeException("product not found in stock");
        }

        checkProductInStock(materialEntries, request.quantity());

        Double remainingQuantityToDeduct = request.quantity();  // talep edilen ürün miktarı
        Double totalCost = 0.0;
        Double totalQuantity = 0.0;

        for (MaterialEntry materialEntry : materialEntries) {
            if (remainingQuantityToDeduct <= 0) {
                break; // Talep edilen tüm miktar karşılanmışsa döngüyü sonlandır
            }

            Double deductedQuantity;

            if (remainingQuantityToDeduct >= materialEntry.getRemainingQuantity()) {
                // Eğer talep edilen miktar, mevcut stoktan fazla ise, o kaydın tamamını alıyoruz
                deductedQuantity = materialEntry.getRemainingQuantity();
                remainingQuantityToDeduct -= deductedQuantity; // Geriye kalan miktarı düşüyoruz
                materialEntryService.updateRemainingQuantity(materialEntry.getId(), deductedQuantity); // Kalan miktar güncelleniyor
            } else {
                // Eğer talep edilen miktar, mevcut stoktan az ise, sadece kalan miktarı alıyoruz
                deductedQuantity = remainingQuantityToDeduct;
                remainingQuantityToDeduct = 0.0; // Talep edilen miktar tamamlanmış oldu
                materialEntryService.updateRemainingQuantity(materialEntry.getId(), deductedQuantity); // Kalan miktar güncelleniyor
            }

            totalCost += deductedQuantity * materialEntry.getUnitPrice(); // Toplam maliyet hesaplanıyor
            totalQuantity += deductedQuantity; // Toplam miktar hesaplanıyor
        }

        Double averageUnitPrice = totalCost / totalQuantity;

        MaterialExit materialExit = new MaterialExit();
        materialExit.setUnitPrice(averageUnitPrice); // burada depodan çıkan ürün birden fazla kalmede var ise alına her kalem ürünleri için birim fiyatlar hesaplanmışve ortalama birim fiyat bulunmuş
        materialExit.setQuantity(totalQuantity); // bir ürünün depodan çıkan miktarı
        materialExit.setTotalPrice(totalCost); // depodan çıkışı verilen ürün için toplam maliyet tutarı
        materialExit.setExitDate(request.exitDate()); // depodan çıkan ürünün depodan çıkış tarihi
        materialExit.setRecipient(request.recipient()); // depodan çıkışı yapan kişi
        materialExit.setProduct(materialEntries.get(0).getProduct()); // depodan çıkışı yapılan ürün
        materialExit.setTotalPerson(request.totalPerson());

        MaterialExit toSave = materialExitRepository.save(materialExit);
        return mapToResponse(toSave);

    }

    @Override
    public MaterialExitResponse updateMaterialExit(MaterialExitCreateRequest request) {
        return null;
    }

    @Override
    public MaterialExitResponse deleteMaterialExit(Long id) {
        MaterialExit toDelete = getMaterialExitById(id);
        materialExitRepository.delete(toDelete);
        return mapToResponse(toDelete);

    }

    @Override
    public MaterialExit getMaterialExitById(Long id) {
        return materialExitRepository.findById(id).orElseThrow(()-> new RuntimeException("material exit not found with id: " + id));
    }

    private MaterialExitResponse mapToResponse(MaterialExit materialExit) {
        MaterialExitResponse response = new MaterialExitResponse(
                materialExit.getUnitPrice(),
                materialExit.getQuantity(),
                materialExit.getTotalPrice(),
                materialExit.getExitDate(),
                materialExit.getRecipient(),
                materialExit.getProduct().getId());
        return response;

    }
    private List<MaterialExitResponse> mapToResponseList(List<MaterialExit> materialExits) {
        List<MaterialExitResponse> responseList = materialExits.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }
    // depodan çıkış verilirken depoda yeteri miktarda olup olmadığı kontrolü
    private void checkProductInStock(List<MaterialEntry> materialEntries, Double requestedQuantity) {
        double totalStockQuantity = materialEntries.stream()
                .mapToDouble(MaterialEntry::getRemainingQuantity)
                .sum();

        if (totalStockQuantity < requestedQuantity) {
            throw new RuntimeException("There is not enough product in the warehouse. Available: "
                    + totalStockQuantity + ", Requested: " + requestedQuantity);
        }
    }


}
