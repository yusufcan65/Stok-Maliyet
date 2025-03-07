package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class MeasurementType extends BaseEntity {  // ölçü birimi

    private String name;

    @OneToOne(mappedBy = "measurementType")
    private Product product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
