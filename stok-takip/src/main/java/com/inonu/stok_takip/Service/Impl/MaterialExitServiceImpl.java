package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Exception.MaterialExit.MaterialExitNotFoundException;
import com.inonu.stok_takip.Repositoriy.MaterialExitRepository;
import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.Service.MaterialExitService;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.entitiy.MaterialEntry;
import com.inonu.stok_takip.entitiy.MaterialExit;
import com.inonu.stok_takip.entitiy.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<MaterialExitResponse> exitMaterials(MaterialExitCreateRequest request){
        Map<Long, Double> productQuantities = request.productQuantities();
        List<MaterialExitResponse> responses = new ArrayList<>();

        checkProductsInStock(productQuantities);

        for (Map.Entry<Long, Double> entry : productQuantities.entrySet()) {
            MaterialExit materialExit = createExitForSingleProduct(entry.getKey(), entry.getValue(), request);
            responses.add(mapToResponse(materialExit));
        }

        return responses;
    }
    private MaterialExit createExitForSingleProduct(Long productId, Double quantity, MaterialExitCreateRequest request) {
        List<MaterialEntry> materialEntries = materialEntryService.getMaterialEntryByProductId(productId);

        List<MaterialEntry> entriesUsedForExit = new ArrayList<>();
        double remainingQuantityToDeduct = quantity;
        double productCost = 0.0;
        double productQuantity = 0.0;

        for (MaterialEntry materialEntry : materialEntries) {
            if (remainingQuantityToDeduct <= 0) {
                break;
            }

            double deductedQuantity = Math.min(remainingQuantityToDeduct, materialEntry.getRemainingQuantity());
            remainingQuantityToDeduct -= deductedQuantity;

            productCost += deductedQuantity * materialEntry.getUnitPrice();
            productQuantity += deductedQuantity;

            entriesUsedForExit.add(materialEntry);
        }

        double averageUnitPrice = (productQuantity > 0) ? (productCost / productQuantity) : 0.0;

        MaterialExit materialExit = buildMaterialExit(entriesUsedForExit.get(0).getProduct(), averageUnitPrice, productQuantity, productCost, request);

        MaterialExit savedExit = materialExitRepository.save(materialExit);

        updateMaterialEntriesStock(entriesUsedForExit, quantity);

        return savedExit;
    }

    private MaterialExit buildMaterialExit(Product product, Double unitPrice, Double quantity,
                                           Double totalPrice, MaterialExitCreateRequest request) {
        MaterialExit materialExit = new MaterialExit();
        materialExit.setProduct(product);
        materialExit.setUnitPrice(unitPrice);
        materialExit.setQuantity(quantity);
        materialExit.setTotalPrice(totalPrice);
        materialExit.setExitDate(request.exitDate());
        materialExit.setRecipient(request.recipient());
        materialExit.setTotalPerson(request.totalPerson());
        return materialExit;
    }
    private void updateMaterialEntriesStock(List<MaterialEntry> entriesUsedForExit, double totalQuantity) {
        double remainingToDeduct = totalQuantity;

        for (MaterialEntry materialEntry : entriesUsedForExit) {
            if (remainingToDeduct <= 0) {
                break;
            }

            double deducted = Math.min(materialEntry.getRemainingQuantity(), remainingToDeduct);
            materialEntryService.updateRemainingQuantity(materialEntry.getId(), deducted);
            remainingToDeduct -= deducted;
        }
    }

    private void checkProductsInStock(Map<Long, Double> productQuantities) {
        for (Map.Entry<Long, Double> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Double quantity = entry.getValue();

            Double stock = materialEntryService.getTotalRemainingQuantity(productId);
            if (stock == null || stock < quantity) {
                throw new RuntimeException("Yetersiz stok! Ürün ID: " + productId);
            }
        }
    }


   /* @Override
    public List<MaterialExitResponse> createMaterialExit(MaterialExitCreateRequest request) {
        Map<Long, Double> productQuantities = request.productQuantities();
        List<MaterialExitResponse> responses = new ArrayList<>();

        // Tüm ürünler için stok kontrolü
        checkProductsInStock(productQuantities);

        // Her bir ürün için ayrı MaterialExit kaydı oluşturulacak
        for (Map.Entry<Long, Double> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Double quantity = entry.getValue(); // Bu üründen çıkması gereken miktar

            // Ürüne ait tüm malzeme girişlerini FIFO sırasına göre alıyoruz
            List<MaterialEntry> materialEntries = materialEntryService.getMaterialEntryByProductId(productId);

            // FIFO mantığıyla malzeme girişlerinden alacağımız miktarı belirliyoruz
            Double remainingQuantityToDeduct = quantity; // Çıkartılacak toplam miktar
            Double productCost = 0.0; // Bu ürünün maliyeti
            Double productQuantity = 0.0; // Bu ürünün toplam miktarı
            List<MaterialEntry> entriesUsedForExit = new ArrayList<>(); // Kullanılan malzeme girişlerini saklıyoruz

            // FIFO sırasına göre malzeme girişleri üzerinde işlem yapıyoruz
            for (MaterialEntry materialEntry : materialEntries) {
                if (remainingQuantityToDeduct <= 0) {
                    break; // Miktar sıfırlandığında işlemi bitiriyoruz
                }

                // FIFO mantığı ile ilk kaydın tamamını alıyoruz ve sonra kalan miktarı alıyoruz
                Double deductedQuantity = Math.min(remainingQuantityToDeduct, materialEntry.getRemainingQuantity());
                remainingQuantityToDeduct -= deductedQuantity; // Kalan miktarı güncelliyoruz

                // Toplam maliyet ve toplam miktar hesaplanıyor
                productCost += deductedQuantity * materialEntry.getUnitPrice();
                productQuantity += deductedQuantity;

                // FIFO sırasıyla tüm malzeme girişlerini ekliyoruz
                entriesUsedForExit.add(materialEntry);
            }

            // Ortalama birim fiyat hesaplanıyor (toplam maliyet / toplam miktar)
            Double averageUnitPrice = productQuantity > 0 ? productCost / productQuantity : 0.0;

            // MaterialExit kaydı oluşturuluyor
            MaterialExit materialExit = new MaterialExit();
            materialExit.setUnitPrice(averageUnitPrice); // Ortalama birim fiyat
            materialExit.setQuantity(productQuantity); // Çıkan toplam miktar
            materialExit.setTotalPrice(productCost); // Toplam maliyet
            materialExit.setExitDate(request.exitDate()); // Çıkış tarihi
            materialExit.setRecipient(request.recipient()); // Çıkışı yapan kişi
            materialExit.setTotalPerson(request.totalPerson()); // Toplam kişi sayısı
            materialExit.setProduct(entriesUsedForExit.get(0).getProduct()); // Ürün bilgisi

            // Malzeme çıkışı kaydediliyor
            MaterialExit savedExit = materialExitRepository.save(materialExit);

            // Her bir malzeme girişinin kalan miktarı güncelleniyor
            for (MaterialEntry materialEntry : entriesUsedForExit) {
                Double deductedQuantity = Math.min(materialEntry.getRemainingQuantity(), productQuantity);
                materialEntryService.updateRemainingQuantity(materialEntry.getId(), deductedQuantity);
            }

            // Response oluşturuluyor
            responses.add(mapToResponse(savedExit));
        }

        // Tüm çıkışları döndürüyoruz
        return responses;
    }
*/

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


    // bundan sonrası fiş ve rapor yapısı için eklenmiştir deneme amaçlı
    @Override
    public int numberMealsBetweenDates(DateRequest dateRequest){
        return materialExitRepository.findTotalPersonsSumBetweenDates(dateRequest.startDate(), dateRequest.endDate());
    }

    @Override
    public Double calculateTotalAmount(DateRequest dateRequest) {
        List<MaterialExitResponse> materialExitResponses = getMaterialListBetweenDate(dateRequest);

        Double totalAmount = 0.0;
        for(MaterialExitResponse materialExitResponse : materialExitResponses){
            totalAmount += materialExitResponse.getTotalPrice();
        }
        return totalAmount;
    }

    @Override
    public List<MaterialExitResponse> getMaterialListBetweenDate(DateRequest dateRequest) {
        List<MaterialExit> materialExits = materialExitRepository.findByMaterialDateBetween(dateRequest.startDate(), dateRequest.endDate());
        return mapToResponseList(materialExits);
    }

    @Override
    public Double calculateCleanMaterialPrice(DateRequest dateRequest){
        return materialExitRepository.findTotalPriceForCleaningCategoryBetweenDates(dateRequest.startDate(), dateRequest.endDate());
    }

    // bundan öncesi tamamen rapor ve fiş yapısını denemek için yapılmıştır

    @Override
    public MaterialExit getMaterialExitById(Long id) {
        return materialExitRepository.findById(id).orElseThrow(()-> new MaterialExitNotFoundException("Material exit not found with id: " + id));
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
  /*  private void checkProductsInStock(Map<Long, Double> productQuantities) {
        List<String> insufficientProducts = new ArrayList<>();

        for (Map.Entry<Long, Double> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Double requestedQuantity = entry.getValue();

            List<MaterialEntry> materialEntries = materialEntryService.getMaterialEntryByProductId(productId);
            double totalStockQuantity = materialEntries.stream()
                    .mapToDouble(MaterialEntry::getRemainingQuantity)
                    .sum();

            if (totalStockQuantity < requestedQuantity) {
                insufficientProducts.add("Product ID " + productId + " - Available: " + totalStockQuantity + ", Requested: " + requestedQuantity);
            }
        }

        if (!insufficientProducts.isEmpty()) {
            throw new RuntimeException("Insufficient stock for the following products:\n" + String.join("\n", insufficientProducts));
        }
    }

*/

}
