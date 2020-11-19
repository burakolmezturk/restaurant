package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findCategoryByName(String categoryName) {
        return productRepository.findCategoryByName(categoryName);
    }

    public List<String> getAllCategory() {
        return productRepository.getAllCategory();
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product editProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }


}
