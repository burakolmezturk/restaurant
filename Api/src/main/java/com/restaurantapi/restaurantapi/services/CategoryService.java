package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.CategoryMapper;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@EnableCaching
@Service
public class CategoryService {

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private MediaRepository mediaRepository;

  @Autowired private CategoryMapper categoryMapper;

  //@Cacheable(value = "CategoryCache")
  public List<CategoryDTO> getCategories() {

    List<Category> categories = categoryRepository.findAll();
    if (categories.isEmpty()) throw new BusinessRuleException(ErrorMessage.RECORD_NOT_FOUND);

    List<CategoryDTO> categoryDTOList = categoryMapper.toDTOList(categories);

    return categoryDTOList;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  //@CacheEvict(value = "CategoryCache", allEntries = true)
  public void addCategory(CategoryDTO categoryDTO) {

    Optional<Media> media = mediaRepository.findById(categoryDTO.getImage().getId());
    if (!media.isPresent()) throw new RecordNotFoundException(ErrorMessage.MEDIA_NOT_FOUND);

    Category category = categoryMapper.toEntity(categoryDTO);
    category.setImage(media.get());

    categoryRepository.save(category);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  //@CacheEvict(value = "CategoryCache", allEntries = true)
  public CategoryDTO editCategory(CategoryDTO categoryDTO) {

    categoryRepository.saveAndFlush(categoryMapper.toEntity(categoryDTO));
    return categoryDTO;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  //@CacheEvict(value = "CategoryCache", allEntries = true)
  public boolean deleteCategory(int id) {

    if (!categoryRepository.existsById(id))
      throw new RecordNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);

    categoryRepository.deleteById(id);
    return true;
  }

  //@Cacheable(value = "Category", key = "#categoryId")
  public CategoryDTO getCategoryById(int categoryId) {

    Optional<Category> category = categoryRepository.findById(categoryId);
    if (!category.isPresent()) throw new RecordNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);

    return categoryMapper.toDTO(category.get());
  }
}
