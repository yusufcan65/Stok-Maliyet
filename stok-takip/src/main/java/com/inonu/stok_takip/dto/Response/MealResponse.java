package com.inonu.stok_takip.dto.Response;

import java.util.List;

public class MealResponse {
    private Long id;
    private Double mealAmount; 
    private String name;
    private int cookingTime;
    private Double servingSize; 
    private String cookingMethod;
    private List<Long> mealItemIdList;

    public MealResponse() {
    }

    public MealResponse(Long id, Double mealAmount, String name, int cookingTime, Double servingSize, String cookingMethod, List<Long> mealItemIdList) {
        this.id = id;
        this.mealAmount = mealAmount;
        this.name = name;
        this.cookingTime = cookingTime;
        this.servingSize = servingSize;
        this.cookingMethod = cookingMethod;
        this.mealItemIdList = mealItemIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMealAmount() {
        return mealAmount;
    }

    public void setMealAmount(Double mealAmount) {
        this.mealAmount = mealAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public void setServingSize(Double servingSize) {
        this.servingSize = servingSize;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public List<Long> getMealItemIdList() {
        return mealItemIdList;
    }

    public void setMealItemIdList(List<Long> mealItemIdList) {
        this.mealItemIdList = mealItemIdList;
    }
}
