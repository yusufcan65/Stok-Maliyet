package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Enum.EntrySourceType;
import com.inonu.stok_takip.Service.DirectProcurementService;
import com.inonu.stok_takip.Service.DirectProcurementTransactionService;
import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;
import com.inonu.stok_takip.entitiy.DirectProcurement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectProcurementTransactionServiceImpl implements DirectProcurementTransactionService {

    private final MaterialEntryService materialEntryService;
    private final DirectProcurementService directProcurementService;

    public DirectProcurementTransactionServiceImpl(MaterialEntryService materialEntryService, DirectProcurementService directProcurementService) {
        this.materialEntryService = materialEntryService;
        this.directProcurementService = directProcurementService;
    }

    @Transactional
    @Override
    public DirectProcurementResponse createDirectAndMaterialEntry(DirectProcurementCreateRequest request) {

        DirectProcurement directProcurement = directProcurementService.createDirectProcurement(request);

        if(request.warehouseTransferQuantity() != null && request.warehouseTransferQuantity() > 0){
            MaterialEntryCreateRequest materialEntryCreateRequest = new MaterialEntryCreateRequest(
                    request.warehouseTransferQuantity(), request.unitPrice(), request.entryDate(),
                    request.expiryDate(), request.companyName(), request.description(),request.productId(),
                    request.budgetId(), EntrySourceType.DOGRUDAN_TEMIN, request.purchaseUnitId(),
                    request.purchaseTypeId(),null, directProcurement.getId(),directProcurement.getPurchaseForm().getId()
            );
            materialEntryService.createMaterialEntry(materialEntryCreateRequest);
        }


        return mapToResponse(directProcurement);
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
}
