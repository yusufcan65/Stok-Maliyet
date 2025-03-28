package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record MaterialEntryCreateRequest(
    Double quantity,
    Double unitPrice,
    LocalDate entryDate,
    LocalDate expiryDate,
    String companyName,
    String Description,
    Long productId,
    Long budgetId,
    Long purchaseUnitId,
    Long purchaseFormId,
    Long purchaseTypeId
) {}
