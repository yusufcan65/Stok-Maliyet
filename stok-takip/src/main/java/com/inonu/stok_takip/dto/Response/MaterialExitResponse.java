package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public class MaterialExitResponse {
    private Double unitPrice;
    private Double quantity;
    private Double totalPrice;
    private LocalDate exitDate;
    private String recipient;
    private Long productId;

    public MaterialExitResponse() {
    }

    public MaterialExitResponse(Double unitPrice, Double quantity, Double totalPrice, LocalDate exitDate, String recipient, Long productId) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.exitDate = exitDate;
        this.recipient = recipient;
        this.productId = productId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
