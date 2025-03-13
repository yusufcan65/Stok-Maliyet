package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;
import java.util.List;

public class MenuResponse {
    private Long id;
    private LocalDate menuDate;
    private Double price; 
    private String menuType;
    private List<Long> menuMealIdList;
    private List<Long> menuProductIdList;

    public MenuResponse() {
    }

    public MenuResponse(Long id, LocalDate menuDate, Double price, String menuType, List<Long> menuMealIdList, List<Long> menuProductIdList) {
        this.id = id;
        this.menuDate = menuDate;
        this.price = price;
        this.menuType = menuType;
        this.menuMealIdList = menuMealIdList;
        this.menuProductIdList = menuProductIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<Long> getMenuMealIdList() {
        return menuMealIdList;
    }

    public void setMenuMealIdList(List<Long> menuMealIdList) {
        this.menuMealIdList = menuMealIdList;
    }

    public List<Long> getMenuProductIdList() {
        return menuProductIdList;
    }

    public void setMenuProductIdList(List<Long> menuProductIdList) {
        this.menuProductIdList = menuProductIdList;
    }
}
