package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.services.CategoryService;
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

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;

    private CategoryDTO categoryDTO = new CategoryDTOBuilder()
            .id(1)
            .description("deneme")
            .name("cate")
            .build();
    List<CategoryDTO> categoryDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        categoryDTOList.add(categoryDTO);
    }

    @Test
    public void ShouldGetCategories() {
        Mockito.when(categoryService.getCategories()).thenReturn(categoryDTOList);
        List<CategoryDTO> res = categoryController.getCategories();
        Assert.assertNotNull(res);
        Assert.assertEquals(res, categoryDTOList);
    }

    @Test
    public void ShouldNotGetCategories() {
        Mockito.when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        List<CategoryDTO> res = categoryController.getCategories();
        Assert.assertNotEquals(res, categoryDTOList);

    }

    @Test
    public void ShouldAddCategory() {
        categoryController.addCategory(categoryDTO);
        Mockito.verify(categoryService, Mockito.times(1)).addCategory(categoryDTO);
    }

    @Test
    public void ShouldUpdateCategory() {
        Mockito.when(categoryService.editCategory(categoryDTO)).thenReturn(categoryDTO);
        CategoryDTO res = categoryController.updateCategory(categoryDTO);
        Assert.assertEquals(res, categoryDTO);
    }

    @Test
    public void ShouldNotUpdateCategory() {
        Mockito.when(categoryService.editCategory(categoryDTO)).thenReturn(new CategoryDTO());
        CategoryDTO res = categoryController.updateCategory(categoryDTO);
        Assert.assertNotEquals(res, categoryDTO);
    }

    @Test
    public void ShouldDeleteCategory() {
        Mockito.when(categoryService.deleteCategory(1)).thenReturn(true);
        Boolean res = categoryController.deleteCategory(1);
        Assert.assertEquals(true, res);
    }

    @Test
    public void ShouldNotDeleteCategory() {
        Mockito.when(categoryService.deleteCategory(1)).thenReturn(false);
        Boolean res = categoryController.deleteCategory(1);
        Assert.assertEquals(false, res);
    }

    @Test
    public void ShouldGetCategoryById() {
        Mockito.when(categoryService.getCategoryById(1)).thenReturn(categoryDTO);
        CategoryDTO res = categoryController.getCategoryById(1);
        Assert.assertEquals(res, categoryDTO);
    }

}
