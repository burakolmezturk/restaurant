package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.convertor.CategoryDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MediaRepository mediaRepository;

    private MediaDTO mediaDTO = new MediaDTOBuilder()
            .fileContent(null)
            .fileName("deneme")
            .id(1).build();
    private CategoryDTO categoryDTO = new CategoryDTOBuilder()
            .id(1)
            .description("deneme")
            .name("cate")
            .image(mediaDTO)
            .build();

    private List<CategoryDTO> categoryDTOList = new ArrayList<>();

    @Before
    public void setUp() {

        categoryDTOList.add(categoryDTO);
    }

    @Test
    public void ShouldAddCategory() {
        Mockito.when(mediaRepository.findById(1)).thenReturn(Optional.of(MediaDTOConvertor.dtoToMedia(mediaDTO)));
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(CategoryDTOConvertor.dtoToCategory(categoryDTO));
        Boolean res = categoryService.addCategory(categoryDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(true, res);

    }

    @Test
    public void NotShouldAddCategory() {
        Mockito.when(mediaRepository.findById(1)).thenReturn(Optional.of(MediaDTOConvertor.dtoToMedia(new MediaDTO())));
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(new Category());
        Boolean res = categoryService.addCategory(categoryDTO);
        Assert.assertNotNull(res);

    }

    @Test
    public void ShouldGetCategories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(CategoryDTOConvertor.dtolisttocategorylist(categoryDTOList));
        List<CategoryDTO> res = categoryService.getCategories();
        Assert.assertNotNull(res);
    }

    @Test
    public void ShouldEditCategory() {
        Mockito.when(categoryRepository.saveAndFlush(Mockito.any())).thenReturn(CategoryDTOConvertor.dtoToCategory(categoryDTO));
        CategoryDTO res = categoryService.editCategory(categoryDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, categoryDTO);
    }

    @Test
    public void ShouldDeleteCategory() {
        int id = 1;
        Mockito.when(categoryRepository.existsById(Mockito.any())).thenReturn(true);
        Boolean res = categoryService.deleteCategory(id);
        Assert.assertEquals(true, res);
    }

    @Test
    public void ShouldNotDeleteCategory() {
        int id = 1;
        Mockito.when(categoryRepository.existsById(Mockito.any())).thenReturn(false);
        Boolean res = categoryService.deleteCategory(id);
        Assert.assertEquals(false, res);
    }

    @Test
    public void ShouldGetCategoryById() {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(CategoryDTOConvertor.dtoToCategory(categoryDTO)));
        CategoryDTO res = categoryService.getCategoryById(1);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), categoryDTO.getId());
    }
}
