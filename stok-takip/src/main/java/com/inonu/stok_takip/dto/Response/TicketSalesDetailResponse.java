package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public class TicketSalesDetailResponse {
    private Long id;
    private int quantity; 
   // private Double totalPrice;
    private LocalDate ticketDate; 
    private Long ticketTypeId;

    public TicketSalesDetailResponse() {
    }

    public TicketSalesDetailResponse(Long id, int quantity,LocalDate ticketDate, Long ticketTypeId) {
        this.id = id;
        this.quantity = quantity;

        this.ticketDate = ticketDate;
        this.ticketTypeId = ticketTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }
}
