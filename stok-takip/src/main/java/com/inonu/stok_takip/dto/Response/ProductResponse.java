package com.inonu.stok_takip.dto.Response;

public class ProductResponse {
    private Long id;
    private String name;
    private Double vatAmount;
    private Double criticalLevel;
    private Long measurementTypeId;
    private Long categoryId;

    public ProductResponse(){}

    public ProductResponse(Long id, String name, Double vatAmount,
                           Double criticalLevel,
                           Long measurementTypeId,Long categoryId){
        this.id=id;
        this.name=name;
        this.vatAmount=vatAmount;
        this.criticalLevel=criticalLevel;
        this.measurementTypeId=measurementTypeId;
        this.categoryId=categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMeasurementTypeId() {
        return measurementTypeId;
    }

    public void setMeasurementTypeId(Long measurementTypeId) {
        this.measurementTypeId = measurementTypeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public Double getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(Double criticalLevel) {
        this.criticalLevel = criticalLevel;
    }


}
