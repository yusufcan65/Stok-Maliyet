package com.inonu.stok_takip.dto.Response;

import java.util.List;

public class CategoryResponse {
    private Long id;
    private String name;
    private List<Long> subCategoryIdList;

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String name, List<Long> subCategoryIdList) {
        this.id = id;
        this.name = name;
        this.subCategoryIdList = subCategoryIdList;
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

    public List<Long> getSubCategoryIdList() {
        return subCategoryIdList;
    }

    public void setSubCategoryIdList(List<Long> subCategoryIdList) {
        this.subCategoryIdList = subCategoryIdList;
    }
}
