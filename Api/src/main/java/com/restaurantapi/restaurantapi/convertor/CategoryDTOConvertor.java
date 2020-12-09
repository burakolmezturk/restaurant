package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.entity.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryDTOConvertor {

    private CategoryDTOConvertor() {
    }

    public static Category dtoToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setImage(categoryDTO.getImage());
        category.setDescription(categoryDTO.getDescription());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static CategoryDTO categoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setImage(category.getImage());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static List<CategoryDTO> categoryListToDTOList(List<Category> categories) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        CategoryDTO categoryDTO;


        for (Category category : categories) {
            categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setImage(category.getImage());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setName(category.getName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public static List<Category> dtolisttocategorylist(List<CategoryDTO> categoriesDTO) {
        List<Category> categoryList = new ArrayList<>();
        Category category;


        for (CategoryDTO categoryDTO : categoriesDTO) {
            category = new Category();
            category.setId(categoryDTO.getId());
            category.setImage(categoryDTO.getImage());
            category.setDescription(categoryDTO.getDescription());
            category.setName(categoryDTO.getName());
            categoryList.add(category);
        }
        return categoryList;
    }

}
