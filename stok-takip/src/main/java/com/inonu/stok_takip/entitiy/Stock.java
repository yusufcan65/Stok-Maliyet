package com.inonu.stok_takip.entitiy;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Stock extends BaseEntity{
    // toplam tutar, alış birim fiyatı, adet, ölçü birimi, alım türü, alım yapılan birim, tadarikçi kurum, kategori,
    // alt kategori , ürün adı, ürün marka adı,
    private int quantity; // adet , miktar
    private Double totalAmount; // toplam tutar
    private Double price; // alınan ürünün birim fiyatı
    private String purchaseType; // alım türü (mal veya hizmet karşılığı vb.)
    private String budgetName; // bütçe adı ( hangi bütçr ile alındığı
    private String purchasingUnit; // alım yapılan birim (SKS , rektörlük , vb.)
    private String modeOfPurchase; // alım şekli (ihale, pazarlık vb.)
    private String productBrand; // ürünün markası
    private String companyName; // ürünün alındığı şirketin adı
    private LocalDate purchaseDate; // alış tarihi   -- stok tarihi olarak ta adlandırılabilir
    private LocalDate expiryDate; // son kullanma tarihi
    @OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getPurchasingUnit() {
        return purchasingUnit;
    }

    public void setPurchasingUnit(String purchasingUnit) {
        this.purchasingUnit = purchasingUnit;
    }

    public String getModeOfPurchase() {
        return modeOfPurchase;
    }

    public void setModeOfPurchase(String modeOfPurchase) {
        this.modeOfPurchase = modeOfPurchase;
    }
}
