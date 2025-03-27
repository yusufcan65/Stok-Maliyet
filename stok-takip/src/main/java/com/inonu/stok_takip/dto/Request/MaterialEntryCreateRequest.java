package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record MaterialEntryCreateRequest(
    Double quantity,
    Double remainingQuantity,
    Double unitPrice,
    Double totalPrice,
    LocalDate entryDate,
    LocalDate expiryDate,
    String companyName,
    Double totalPriceIncludingVat,
    String Description,
    Long productId,
    Long budgetId,
    Long purchaseUnitId,
    Long purchaseFormId,
    Long purchaseTypeId
) {}
