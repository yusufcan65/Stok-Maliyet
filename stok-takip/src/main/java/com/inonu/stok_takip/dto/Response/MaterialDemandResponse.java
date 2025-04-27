package com.inonu.stok_takip.dto.Response;

import com.inonu.stok_takip.Enum.DemandStatus;

import java.time.LocalDate;

public class MaterialDemandResponse {
    private Long id;
    private Double quantity;
    private Long userId;
    private String companyName;
    private LocalDate requestDate;
    private Long productId;
    private DemandStatus demandStatus;

    public MaterialDemandResponse() {
    }

    public MaterialDemandResponse(Long id, Double quantity, Long userId, String companyName, LocalDate requestDate, Long productId,DemandStatus demandStatus) {
        this.id = id;
        this.quantity = quantity;
        this.userId = userId;
        this.companyName = companyName;
        this.requestDate = requestDate;
        this.productId = productId;
        this.demandStatus = demandStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public DemandStatus getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(DemandStatus demandStatus) {
        this.demandStatus = demandStatus;
    }
}
