package com.inonu.stok_takip.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class MaterialEntry extends BaseEntity {


    private Double quantity;// giriş miktarı // adet
    private Double remainingQuantity; // stokta kalan miktar
    private Double unitPrice; // ürünün birim fiyatı
    private Double totalPrice; // alınan ürünün toplam tutarı = adet* birim fiyatı
    private LocalDate entryDate; // giriş tarihi // fatura tarihi
    private LocalDate expiryDate; // ürün son kullanma tarihi
    private String companyName; // firma adı
    private Double totalPriceIncludingVat; // kdv dahil toplam tutar
    private String description; // stoğa giriş yapılan ürün için açıklama 



    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    @JsonIgnore
    private Product product; // product name

    @ManyToOne
    @JoinColumn(name = "budget_id",nullable = false)
    private Budget budget;  // bütçe adı

    @ManyToOne
    @JoinColumn(name = "purchaseUnit_id")
    private PurchasedUnit purchasedUnit; //alım yapılan birim

    @ManyToOne
    @JoinColumn(name = "purchaseForm_id")
    private PurchaseForm purchaseForm; // alım şekli

    @ManyToOne
    @JoinColumn(name = "purchaseType_id")
    private PurchaseType purchaseType; // alım türü

    public Double getQuantity() {

        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Double remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Budget getBudget() {

        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getTotalPriceIncludingVat() {
        return totalPriceIncludingVat;
    }

    public void setTotalPriceIncludingVat(Double totalPriceIncludingVat) {
        this.totalPriceIncludingVat = totalPriceIncludingVat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PurchasedUnit getPurchasedUnit() {
        return purchasedUnit;
    }

    public void setPurchasedUnit(PurchasedUnit purchasedUnit) {
        this.purchasedUnit = purchasedUnit;
    }

    public PurchaseForm getPurchaseForm() {
        return purchaseForm;
    }

    public void setPurchaseForm(PurchaseForm purchaseForm) {
        this.purchaseForm = purchaseForm;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }
}
