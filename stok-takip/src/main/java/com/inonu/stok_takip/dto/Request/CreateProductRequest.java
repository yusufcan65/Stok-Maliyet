package com.inonu.stok_takip.dto.Request;

public record CreateProductRequest(
    String name,
    Long measurementTypeID,
    Long categoryId,
    Long SubCategoryId
    ) {}