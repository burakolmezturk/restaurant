package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/add")
    public void addCategory(@Valid @RequestBody @NotNull(message = "{CATEGORY_NOT_FOUND}") CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/edit")
    public CategoryDTO updateCategory(@Valid @RequestBody @NotNull(message = "{CATEGORY_NOT_FOUND}") CategoryDTO categoryDTO) {
        return categoryService.editCategory(categoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int id) {
        if (categoryService.deleteCategory(id)) return true;
        else return false;

    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int categoryId) {
        return categoryService.getCategoryById(categoryId);

    }
}
