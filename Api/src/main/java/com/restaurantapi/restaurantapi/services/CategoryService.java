package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Optional<Category> getCategoryById(int categoryId){
        Optional<Category> category =categoryRepository.findById(categoryId);
        if (category.isPresent()){
            return category;
        }else{
            return null;
        }

    }
}
