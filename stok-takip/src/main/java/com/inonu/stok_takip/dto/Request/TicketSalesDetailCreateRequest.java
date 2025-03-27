package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record TicketSalesDetailCreateRequest(
    int quantity,
    Double totalPrice,
    LocalDate ticketDate,
    Long ticketTypeId
) {
    
}
