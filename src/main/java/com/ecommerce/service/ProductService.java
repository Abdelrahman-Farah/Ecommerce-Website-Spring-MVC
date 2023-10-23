package com.ecommerce.service;

import com.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    Product findById(int productId);
    List<Product> findAll();

    Product save(Product product);

    void deleteById(int productId);
}
