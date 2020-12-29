package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
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
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDTO categoryDTO) {
       categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/edit")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.editCategory(categoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable int id) {
        if(categoryService.deleteCategory(id))  return true;
        else return false;

    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable int categoryId) {
        return categoryService.getCategoryById(categoryId);

    }
}
