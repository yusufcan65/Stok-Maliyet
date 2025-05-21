package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public record DirectProcurementResponse(
        Long id,
        Double quantity,
        Double remainingQuantity,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active,
        Double unitPrice,
        Double totalAmount,
        String companyName,
        Long productId,
        String productName,
        String measurementTypeName,
        String purchasedUnitName,
        String purchaseTypeName,
        String purchaseFormName
) {
}
