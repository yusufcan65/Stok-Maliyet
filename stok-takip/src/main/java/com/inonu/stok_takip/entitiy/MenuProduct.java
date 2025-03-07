package com.inonu.stok_takip.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_product")
public class MenuProduct extends BaseEntity {

    private int quantity; // kullanlacak ürün miktarı ( 1 su, 2 ekmek vb.)

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
