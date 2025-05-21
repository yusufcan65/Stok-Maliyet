package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.DirectProcurementRepository;
import com.inonu.stok_takip.Service.*;
import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;
import com.inonu.stok_takip.entitiy.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DirectProcurementServiceImpl implements DirectProcurementService {

    private final DirectProcurementRepository directProcurementRepository;
    private final PurchaseFormService purchaseFormService;
    private final PurchasedUnitService purchasedUnitService;
    private final PurchaseTypeService purchaseTypeService;
    private final ProductService productService;

    public DirectProcurementServiceImpl(DirectProcurementRepository directProcurementRepository,
                                        PurchaseFormService purchaseFormService, PurchasedUnitService purchasedUnitService,
                                        PurchaseTypeService purchaseTypeService, ProductService productService) {
        this.directProcurementRepository = directProcurementRepository;
        this.purchaseFormService = purchaseFormService;
        this.purchasedUnitService = purchasedUnitService;
        this.purchaseTypeService = purchaseTypeService;
        this.productService = productService;
    }


    @Override
    public DirectProcurement createDirectProcurement(DirectProcurementCreateRequest request) {

        PurchaseForm purchaseForm = purchaseFormService.getPurchaseFormById(request.purchaseFormId());
        PurchaseType purchaseType = purchaseTypeService.getPurchaseTypeById(request.purchaseTypeId());

        PurchasedUnit purchasedUnit = purchasedUnitService.getPurchasedUnitById(request.purchaseUnitId());
        Product product = productService.getProductById(request.productId());

        DirectProcurement directProcurement = mapToEntity(request);
        Double totalAmount = directProcurement.getUnitPrice() * directProcurement.getQuantity();
        directProcurement.setTotalAmount(totalAmount);
        directProcurement.setPurchasedUnit(purchasedUnit);
        directProcurement.setProduct(product);
        directProcurement.setPurchaseType(purchaseType);
        directProcurement.setPurchaseForm(purchaseForm);
        Double remainingQuantity = request.quantity() - request.warehouseTransferQuantity();
        directProcurement.setRemainingQuantity(remainingQuantity);

        DirectProcurement saved = directProcurementRepository.save(directProcurement);

        return saved;
    }

    @Override
    public List<DirectProcurementResponse> getAllDirectProcurements() {
        List<DirectProcurement> directProcurements = directProcurementRepository.findAll();
        return mapToResponseList(directProcurements);
    }

    @Override
    public DirectProcurement getDirectProcurementById(Long id) {

        return directProcurementRepository.findById(id).orElseThrow(()-> new RuntimeException("direct procurement not found by ıd: "+id));
    }

    @Override
    public DirectProcurementResponse updateRemainingQuantity(Long directProcurementId, Double quantity) {
        DirectProcurement directProcurement = getDirectProcurementById(directProcurementId);
        directProcurement.setRemainingQuantity(directProcurement.getRemainingQuantity()- quantity);
        DirectProcurement saved = directProcurementRepository.save(directProcurement);

        return mapToResponse(saved);
    }

    @Override
    public List<DirectProcurementResponse> getDirectProcurementsByProductAndCompany() {
        List<DirectProcurement> directProcurements = directProcurementRepository.findByRemainingQuantityGreaterThan(0.0);
        return mapToResponseList(directProcurements);
    }

    private DirectProcurementResponse mapToResponse(DirectProcurement directProcurement) {
        DirectProcurementResponse directProcurementResponse = new DirectProcurementResponse(
                directProcurement.getId(),
                directProcurement.getQuantity(),
                directProcurement.getRemainingQuantity(),
                directProcurement.getStartDate(),
                directProcurement.getEndDate(),
                directProcurement.isActive(),
                directProcurement.getUnitPrice(),
                directProcurement.getTotalAmount(),
                directProcurement.getCompanyName(),
                directProcurement.getProduct().getId(),
                directProcurement.getProduct().getName(),
                directProcurement.getProduct().getMeasurementType().getName(),
                directProcurement.getPurchasedUnit().getName(),
                directProcurement.getPurchaseType().getName(),
                directProcurement.getPurchaseForm().getName()
        );
        return directProcurementResponse;
    }
    private List<DirectProcurementResponse> mapToResponseList(List<DirectProcurement> directProcurementList) {
        List<DirectProcurementResponse> directProcurementResponseList = directProcurementList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return directProcurementResponseList;
    }
    private DirectProcurement mapToEntity(DirectProcurementCreateRequest request){
        DirectProcurement directProcurement = new DirectProcurement();
        directProcurement.setQuantity(request.quantity());
        directProcurement.setStartDate(request.entryDate());
        directProcurement.setEndDate(request.expiryDate());
        directProcurement.setCompanyName(request.companyName());
        directProcurement.setUnitPrice(request.unitPrice());
        return directProcurement;
    }




}
