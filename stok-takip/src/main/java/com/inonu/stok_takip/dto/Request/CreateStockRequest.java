package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record CreateStockRequest(
    int quantity,
    Double totalAmount,
    Double price,
    String purchaseType,
    String budgetName,
    String purchasingUnit,
    String modeOfPurchase,
    String productBrand,
    String companyName,
    LocalDate purchaseDate,
    LocalDate expiryDate,
    Long productId
){}