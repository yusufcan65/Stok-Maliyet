package com.inonu.stok_takip.dto.Request;

public record CreateMenuProductRequest(
    int quantity,
    Long productId
) {}
