package com.inonu.stok_takip.dto.Response;

public class PurchaseFormResponse {

    private Long id;
    private String name;

    public PurchaseFormResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PurchaseFormResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
