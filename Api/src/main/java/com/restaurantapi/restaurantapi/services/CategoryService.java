package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import com.restaurantapi.restaurantapi.repository.CartRepository;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public Category editCategory(Category category){
       return categoryRepository.saveAndFlush(category);
    }
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
    public Category getCategoryById(int categoryId){
        return categoryRepository.findById(categoryId).get();
    }
}
