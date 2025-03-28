package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public class MaterialEntryResponse {
    private Double quantity;
    private Double unitPrice;
    private LocalDate entryDate; 
    private LocalDate expiryDate;
    private String companyName;
    private String description;
    private Double totalPrice;
    private Double totalPriceIncludingVat;
    private Long productId;
    private Long purchaseTypeId;
    private Long purchasedUnitId;
    private Long purchaseFormId;

    public MaterialEntryResponse() {
    }

    public MaterialEntryResponse(Double quantity, Double unitPrice,
                                 LocalDate entryDate, LocalDate expiryDate,
                                 String companyName, String description,
                                 Double totalPrice, Double totalPriceIncludingVat,
                                 Long productId, Long purchaseTypeId,
                                 Long purchasedUnitId, Long purchaseFormId) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.companyName = companyName;
        this.description = description;
        this.totalPrice = totalPrice;
        this.totalPriceIncludingVat = totalPriceIncludingVat;
        this.productId = productId;
        this.purchaseTypeId = purchaseTypeId;
        this.purchasedUnitId = purchasedUnitId;
        this.purchaseFormId = purchaseFormId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPurchasedUnitId() {
        return purchasedUnitId;
    }

    public void setPurchasedUnitId(Long purchasedUnitId) {
        this.purchasedUnitId = purchasedUnitId;
    }

    public Long getPurchaseFormId() {
        return purchaseFormId;
    }

    public void setPurchaseFormId(Long purchaseFormId) {
        this.purchaseFormId = purchaseFormId;
    }

    public Long getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(Long purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPriceIncludingVat() {
        return totalPriceIncludingVat;
    }

    public void setTotalPriceIncludingVat(Double totalPriceIncludingVat) {
        this.totalPriceIncludingVat = totalPriceIncludingVat;
    }
}
