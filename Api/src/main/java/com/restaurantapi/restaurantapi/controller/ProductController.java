package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getAllProducts() {

        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {

        List<Product> articles = productService.getProductById(id);

        return articles.get(0);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {

        return productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product putProduct(@RequestBody Product product) {
      return   productService.editProduct(product);

    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return true;
    }


}
