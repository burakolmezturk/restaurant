package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product editProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

}
