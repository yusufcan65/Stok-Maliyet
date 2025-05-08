package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record TicketSalesDetailCreateRequest(
    //Deneme Amaclı
    int quantity,
    //Double totalPrice,
    LocalDate ticketDate,
    Long ticketTypeId
) {
    
}
