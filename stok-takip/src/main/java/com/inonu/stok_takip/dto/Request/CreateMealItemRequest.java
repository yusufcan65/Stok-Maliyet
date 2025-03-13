package com.inonu.stok_takip.dto.Request;

public record CreateMealItemRequest(
    Double productQuantity,
    Double price,
    Long productId
) {}
