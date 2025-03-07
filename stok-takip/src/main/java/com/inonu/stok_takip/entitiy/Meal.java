package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Meal extends BaseEntity{


    private Double mealAmount; // 1 porsiyon tutarı (bir tabak yemeğin tutarı)
    private String name;

    @OneToMany(mappedBy = "meal")
    private List<MealItem> mealItems;

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

    public List<MealItem> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }
}
