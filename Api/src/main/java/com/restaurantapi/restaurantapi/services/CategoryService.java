package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.convertor.CategoryDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MediaRepository mediaRepository;

    public List<CategoryDTO> getCategories() {


        return CategoryDTOConvertor.categoryListToDTOList(categoryRepository.findAll());

    }

    public boolean addCategory(CategoryDTO categoryDTO) {
        Media media = mediaRepository.findById(categoryDTO.getImage().getId()).get();
        Category category = CategoryDTOConvertor.dtoToCategory(categoryDTO);
        category.setImage(media);
        categoryRepository.save(category);

        if (category.getId() != 0) return true;
        else return false;
    }

    public CategoryDTO editCategory(CategoryDTO categoryDTO) {

        categoryRepository.saveAndFlush(CategoryDTOConvertor.dtoToCategory(categoryDTO));
        return categoryDTO;
    }

    public boolean deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else return false;

    }

    public CategoryDTO getCategoryById(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            return null;
        }
        return CategoryDTOConvertor.categoryToDTO(category.get());

    }
}
