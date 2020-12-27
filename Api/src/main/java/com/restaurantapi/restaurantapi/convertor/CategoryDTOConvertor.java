package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Media;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CategoryDTOConvertor {

    private CategoryDTOConvertor() {
    }

    public static Category dtoToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        if(categoryDTO.getImage()!=null)
        category.setImage(MediaDTOConvertor.dtoToMedia(categoryDTO.getImage()));
        else category.setImage(null);
        category.setDescription(categoryDTO.getDescription());
        category.setName(categoryDTO.getName());
        category.setProducts(new ArrayList<>());
        return category;
    }

    public static CategoryDTO categoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        if (category.getImage()!=null)
        categoryDTO.setImage(MediaDTOConvertor.mediaToDTO(category.getImage()));
        else categoryDTO.setImage(null);
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setName(category.getName());
       // categoryDTO.setProducts(new HashSet<>());
        return categoryDTO;
    }

    public static List<CategoryDTO> categoryListToDTOList(List<Category> categories) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        CategoryDTO categoryDTO;


        for (Category category : categories) {
            categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setImage(MediaDTOConvertor.mediaToDTO(category.getImage()) );
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
            category.setImage(MediaDTOConvertor.dtoToMedia(categoryDTO.getImage()));
            category.setDescription(categoryDTO.getDescription());
            category.setName(categoryDTO.getName());
            categoryList.add(category);
        }
        return categoryList;
    }

}
