package com.inonu.stok_takip.dto.Response;

public class SubCategoryResponse {
    private Long id;
    private String name;
    private Long categoryId;
    
    public SubCategoryResponse(Long id,String name,Long categoryId){
        this.id=id;
        this.categoryId=categoryId;
        this.name=name;
    }

    public SubCategoryResponse(){}

    public Long getId() {
        return id;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public String getName() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setName(String name) {
        this.name = name;
    }
}