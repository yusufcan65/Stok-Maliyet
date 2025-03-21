package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Product extends BaseEntity{

    private String name;  // Malzeme adı
    private Double vatAmount; // ürün kdv miktarı
    private Double criticalLevel; // kritik seviye

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)  // malzeme kategorisi örneğin (bakliyat, temizlik, kru meyve vb.)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "measurementType_id",nullable = false) //malzeme ölçü birimi
    private MeasurementType measurementType;

    // yeni eklenenler

    @OneToMany(mappedBy = "product")
    private List<MaterialEntry> materialEntry;

    @OneToMany(mappedBy = "product")
    private List<MaterialExit> materialExits;

    @OneToMany(mappedBy = "product")
    private List<MaterialDemand> materialDemands;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<MaterialEntry> getMaterialEntry() {
        return materialEntry;
    }

    public void setMaterialEntry(List<MaterialEntry> materialEntry) {
        this.materialEntry = materialEntry;
    }

    public List<MaterialExit> getMaterialExits() {
        return materialExits;
    }

    public void setMaterialExits(List<MaterialExit> materialExits) {
        this.materialExits = materialExits;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public List<MaterialDemand> getMaterialDemands() {
        return materialDemands;
    }

    public void setMaterialDemands(List<MaterialDemand> materialDemands) {
        this.materialDemands = materialDemands;
    }

    public Double getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(Double criticalLevel) {
        this.criticalLevel = criticalLevel;
    }
}
