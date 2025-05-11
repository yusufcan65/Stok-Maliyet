package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public record TenderResponse (
        Long id,
        Double tenderQuantity,
        Double remainingQuantityInTender,
        LocalDate startDate,
        LocalDate endDate,
        Double unitPrice,
        Double totalAmount,
        String companyName,
        Long productId,
        String productName,
        String measurementTypeName,
        Long purchasedUnitId,
        Long purchaseTypeId,
        Long purchaseFormId){
}
