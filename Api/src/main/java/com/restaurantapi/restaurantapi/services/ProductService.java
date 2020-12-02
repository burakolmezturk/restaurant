package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    public Product addProduct(Product product, int categoryId) {
        Set<Product> products = new HashSet<>();
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            return null;
        }
        products.add(product);
        category.get().getProductSet().add(product);
        product.setCategory(category.get());
        return productRepository.save(product);
    }


    public Product editProduct(Product product) {


        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return null;
        }
        return productOptional.get();
    }

    public boolean sellProduct(List<Cart> listCart) {
        cartRepository.saveAll(listCart);
        return true;
    }

    public Set<Product> getProductsByCategoryId(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getProductSet();
        } else {
            return Collections.emptySet();
        }

    }

}
