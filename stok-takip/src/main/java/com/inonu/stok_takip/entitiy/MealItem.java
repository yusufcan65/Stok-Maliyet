package com.inonu.stok_takip.entitiy;

import jakarta.persistence.*;

@Entity
public class MealItem extends BaseEntity {

    private Double productQuantity; // Kullanılan ürün miktarı
    private Double price;// her ürünün birim fiyatı

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
