package com.inonu.stok_takip.entitiy;

import jakarta.persistence.*;


@Entity
public class Product extends BaseEntity{

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "measurementType_id", nullable = false)
    private MeasurementType measurementType;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id",nullable = false)
    private SubCategory subCategory;

    @OneToOne(mappedBy = "product")
    private MealItem mealItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public MealItem getMealItem() {
        return mealItem;
    }

    public void setMealItem(MealItem mealItem) {
        this.mealItem = mealItem;
    }
}
