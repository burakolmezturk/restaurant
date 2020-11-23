package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/products")
    public List<Product> findProductByCategory(@RequestParam String categoryName) {
        return productService.findCategoryByName(categoryName);
    }

    @GetMapping("/list/category")
    public List<Product.Category> getAllCategory() {
        Product.Category category = new Product.Category();
        List<String> list = productService.getAllCategory();
        List<Product.Category> categoryList = new ArrayList<>();
        for (String ctr : list) {
            category = new Product.Category();
            category.setName(ctr);
            categoryList.add(category);
        }
        return categoryList;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public Product putProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/cart")
    public boolean sellProduct(@RequestBody List<Cart> carts) {
        productService.sellProduct(carts);
        return true;
    }


}
