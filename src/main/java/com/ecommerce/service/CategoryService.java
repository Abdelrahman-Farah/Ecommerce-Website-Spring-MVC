package com.ecommerce.service;

import com.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category category);

    Category findById(int categoryId);

    void deleteById(int categoryId);
}
