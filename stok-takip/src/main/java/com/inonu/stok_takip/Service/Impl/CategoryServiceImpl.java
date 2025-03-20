package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


}
