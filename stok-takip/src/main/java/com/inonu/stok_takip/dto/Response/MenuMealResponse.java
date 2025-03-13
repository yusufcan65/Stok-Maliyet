package com.inonu.stok_takip.dto.Response;

public class MenuMealResponse {
    private Long id;
    private Long mealId;
    private Long menuId;

    public MenuMealResponse() {
    }

    public MenuMealResponse(Long id, Long mealId, Long menuId) {
        this.id = id;
        this.mealId = mealId;
        this.menuId = menuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
