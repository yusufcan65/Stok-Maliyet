package com.inonu.stok_takip.dto.Request;

public record ProductCreateRequest(
    String name,
    Double vatAmount,
    Double criticalLevel,
    Long measurementTypeId,
    Long categoryId
    ) {}