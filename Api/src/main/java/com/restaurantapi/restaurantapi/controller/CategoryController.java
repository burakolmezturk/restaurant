package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }
    @PostMapping("/add")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }
    @PutMapping("/edit")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.editCategory(category);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable int categoryId){
        return categoryService.getCategoryById(categoryId).get();
    }
}
