package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product,@RequestParam int categoryId) {
        return productService.addProduct(product,categoryId);
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
    @GetMapping("/products")
    public Set<Product> getProductsByCategoryId(@RequestParam int categoryId)
    {
      return   productService.getProductsByCategoryId(categoryId);
    }


}
