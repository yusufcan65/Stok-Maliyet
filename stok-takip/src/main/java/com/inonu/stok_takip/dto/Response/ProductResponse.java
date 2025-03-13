package com.inonu.stok_takip.dto.Response;

public class ProductResponse {
    private Long id;
    private String name;
    private Long measurementTypeId;
    private Long categoryId;
    private Long subCategoryId;

    public ProductResponse(){}

    public ProductResponse(Long id, String name, Long measurementTypeId,Long categoryId,Long subCategoryId){
        this.id=id;
        this.name=name;
        this.measurementTypeId=measurementTypeId;
        this.categoryId=categoryId;
        this.subCategoryId=subCategoryId;
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

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
