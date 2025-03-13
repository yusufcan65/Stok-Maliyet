package com.inonu.stok_takip.dto.Response;

public class MenuProductResponse {
    private Long id;
    private int quantity;
    private Long productId;
    private Long menuId;

    public MenuProductResponse() {
    }

    public MenuProductResponse(Long id, int quantity, Long productId, Long menuId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.menuId = menuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
