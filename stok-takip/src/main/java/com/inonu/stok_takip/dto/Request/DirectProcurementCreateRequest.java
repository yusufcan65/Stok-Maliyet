package com.inonu.stok_takip.dto.Request;

import com.inonu.stok_takip.Enum.EntrySourceType;

import java.time.LocalDate;

public record DirectProcurementCreateRequest(
        Long productId,
        Double quantity,
        Double warehouseTransferQuantity,
        LocalDate entryDate,
        LocalDate expiryDate,
        Double unitPrice,
        String companyName,
        Long purchaseUnitId,
        Long purchaseTypeId,
        Long purchaseFormId,
        String description,
        Long budgetId,
        EntrySourceType entrySourceType
) {
}
