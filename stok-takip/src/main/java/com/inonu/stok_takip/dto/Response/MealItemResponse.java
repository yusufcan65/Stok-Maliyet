package com.inonu.stok_takip.dto.Response;

public class MealItemResponse {
    private Long id;
    private Double productQuantity;
    private Double price;
    private Long productId;
    private Long mealId;

    public MealItemResponse() {
    }

    public MealItemResponse(Long id, Double productQuantity, Double price, Long productId, Long mealId) {
        this.id = id;
        this.productQuantity = productQuantity;
        this.price = price;
        this.productId = productId;
        this.mealId = mealId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Double productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }
}
