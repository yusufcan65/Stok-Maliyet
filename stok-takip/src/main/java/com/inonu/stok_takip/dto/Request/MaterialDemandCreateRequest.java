package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record MaterialDemandCreateRequest(
    Double quantity,
    Long userId,
    String companyName,
    LocalDate requestDate,
    Long productId
) {}
