package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Meal extends BaseEntity{


    private Double mealAmount; // 1 porsiyon tutarı (bir tabak yemeğin tutarı)
    private String name;
    private int cookingTime; // pişirme süresi
    private Double servingSize; // porsiyon ölçüsü ( tabak yemeğin toplam ağırlığı)
    private String cookingMethod; // pişirme metodu (fırın / tencere vb.)

    @OneToMany(mappedBy = "meal")
    private List<MealItem> mealItems;

    @OneToMany(mappedBy = "meal")
    private List<MenuMeal> menuMeals;

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

    public List<MealItem> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }
}
