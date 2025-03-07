package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Menu extends BaseEntity{

    private LocalDate menuDate; // menünün hangi güne ait olduğu
    private Double price; // menü birim tutarı (1 kişilik menünün tutarı)
    private String menuType; // burada menü tipi yer alacak. personel, öğrenci, çölyak hastaları
    // için farklı menüler oluşturulacak bu nedenle 3 çeşit menü olacak ve stoktan 1 günlük 3 menü
    // üzerinden mal çıkışı olacak

    @OneToMany(mappedBy = "menu")
    private List<MenuMeal> menuMeals;

    @OneToMany(mappedBy = "menu")
    private List<MenuProduct> menuProducts;


    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<MenuMeal> getMenuMeals() {
        return menuMeals;
    }

    public void setMenuMeals(List<MenuMeal> menuMeals) {
        this.menuMeals = menuMeals;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public void setMenuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

}
