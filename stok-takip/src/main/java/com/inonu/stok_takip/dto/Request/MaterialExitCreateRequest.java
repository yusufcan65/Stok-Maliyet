package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record MaterialExitCreateRequest(
    Double unitPrice,
    Double quantity,
    Double totalPrice,
    String recipient,
    int totalPerson,
    Long productId,
    LocalDate exitDate
) {
    
}
