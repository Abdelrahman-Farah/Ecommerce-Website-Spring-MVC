package com.ecommerce.service;

import com.ecommerce.dao.ProductRepository;
import com.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(int productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent())
            return product.get();
        else
            throw new RuntimeException("Did not find product id - " + productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }
}
