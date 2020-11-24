package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        products.add(product);
        category.get().getProductSet().add(product);

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

    public boolean sellProduct(List<Cart> listCart) {
        cartRepository.saveAll(listCart);
        return true;
    }
    public Set<Product>  getProductsByCategoryId(int categoryId){
       return categoryRepository.findById(categoryId).get().getProductSet();
    }

}
