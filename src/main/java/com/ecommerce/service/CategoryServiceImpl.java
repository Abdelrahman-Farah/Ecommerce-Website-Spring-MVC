package com.ecommerce.service;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent())
            return category.get();
        else
            throw new RuntimeException("Did not find product id - " + categoryId);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
