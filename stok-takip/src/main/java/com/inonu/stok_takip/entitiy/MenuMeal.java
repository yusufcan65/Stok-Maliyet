package com.inonu.stok_takip.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_meal")
public class MenuMeal extends BaseEntity{
    
    @ManyToOne
    @JoinColumn(name = "menu_id") // hangi menüye ait olduğunu belirtir
    private Menu menu;

    @OneToOne
    @JoinColumn(name = "meal_id") // menüde hangi yemeğin olacağını belirtir
    private Meal meal;


    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }


    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


}
