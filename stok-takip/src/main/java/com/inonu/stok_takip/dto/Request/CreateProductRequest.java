package com.inonu.stok_takip.dto.Request;

public record CreateProductRequest(
    String name,
    Long measurementTypeId,
    Long categoryId
    ) {}