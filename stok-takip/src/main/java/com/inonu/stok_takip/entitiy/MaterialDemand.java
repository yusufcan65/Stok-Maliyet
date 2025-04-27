package com.inonu.stok_takip.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inonu.stok_takip.Enum.DemandStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MaterialDemand extends BaseEntity{

    private Double quantity; // talep edilen malzeme miktarı
    private Long userId; // bu veri zero trusttan çekilecek veya bu veri kullancı tarafından girilecek çünkü bu yapıyı kullanan kişi uygulama kullanıcısı olmayabilir

    private String companyName; // talep edilen firmanın adı
    private LocalDate requestDate; // talep tarihi



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // talep edilen ürün
    private Product product;

    @ManyToOne
    @JoinColumn(name = "tender_id", nullable = false)
    private Tender tender; // Hangi ihaleden talep yapılıyor


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchaseForm_id", nullable = false)
    private PurchaseForm purchaseForm;

    @Enumerated(EnumType.STRING)
    private DemandStatus status = DemandStatus.PENDING; // Varsayılan olarak talep bekliyor (pending) olacaktır.

    private String rejectionReason;


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseForm getPurchaseForm() {
        return purchaseForm;
    }

    public void setPurchaseForm(PurchaseForm purchaseForm) {
        this.purchaseForm = purchaseForm;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }
}
