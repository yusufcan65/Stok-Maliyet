package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.CreateCategoryRequest;
import com.inonu.stok_takip.dto.Response.CategoryResponse;
import com.inonu.stok_takip.entitiy.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> GetAllCategories();
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse updateCategory(CreateCategoryRequest createCategoryRequest);
    Category getCategoryById(Long categoryId);
    CategoryResponse deleteCategory(Long categoryId);


}
