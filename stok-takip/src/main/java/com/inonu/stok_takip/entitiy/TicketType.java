package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class TicketType extends BaseEntity {

    private String name;  // fiş tipi adı  (öğrenci , akademik, vb.)
    private Double unitPrice; // örneğin öğrenci 20 tl, akademisyen 55 tl gibi

    @OneToOne(mappedBy = "ticketType")
    private TicketSalesDetail ticketSalesDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public TicketSalesDetail getTicketSalesDetail() {
        return ticketSalesDetail;
    }

    public void setTicketSalesDetail(TicketSalesDetail ticketSalesDetail) {
        this.ticketSalesDetail = ticketSalesDetail;
    }
}
