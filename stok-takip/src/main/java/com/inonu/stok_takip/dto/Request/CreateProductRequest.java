package com.inonu.stok_takip.dto.Request;

public record CreateProductRequest(
    String name,
    Double vatAmount,
    Long measurementTypeId,
    Long categoryId
    ) {}