package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.CartDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.services.CategoryService;
import com.restaurantapi.restaurantapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/update")
    public ProductDTO putProduct(@RequestBody ProductDTO productDTO, @RequestParam int categoryId) {

        return productService.editProduct(productDTO,categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable int id) {
        if(productService.deleteProduct(id))
            return true;
        else return false;
    }

    @PostMapping("/cart")
    public boolean sellProduct(@RequestBody List<CartDTO> cartDTOList) {

        if (productService.sellProduct(cartDTOList)) return true;
        else return false;

    }

    @GetMapping("/products")
    public Set<ProductDTO> getProductsByCategoryId(@RequestParam int categoryId) {

        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/getPage")
    public Page<ProductDTO> getFindPage(Pageable pageable){
        return productService.getFindPage(pageable);
    }

    @GetMapping("/getSlice")
    public Slice<ProductDTO> getFindSlice(Pageable pageable,int id){
        return productService.getProductWithSlice(pageable,id);
    }

}
