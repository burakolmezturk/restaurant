package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.CategoryDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.convertor.CategoryDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.CategoryMapper;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.repository.CategoryRepository;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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

    @Spy
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

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
    private List<Category> categories = new ArrayList<>();
    private Category category = categoryMapper.toEntity(categoryDTO);
    private Media media = mediaMapper.toEntity(mediaDTO);

    @Before
    public void setUp() {

        categoryDTOList.add(categoryDTO);
        categories = categoryMapper.toEntityList(categoryDTOList);
    }

    @Test
    public void ShouldAddCategory() {
        Mockito.when(mediaRepository.findById(1)).thenReturn(Optional.of(media));
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        categoryService.addCategory(categoryDTO);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }



    @Test
    public void ShouldGetCategories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> res = categoryService.getCategories();
        Assert.assertNotNull(res);
    }

    @Test
    public void ShouldEditCategory() {
        Mockito.when(categoryRepository.saveAndFlush(Mockito.any())).thenReturn(category);
        CategoryDTO res = categoryService.editCategory(categoryDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, categoryDTO);
    }

    @Test
    public void ShouldDeleteCategory() {
        int id = 1;
        Mockito.when(categoryRepository.existsById(id)).thenReturn(true);
        Boolean res = categoryService.deleteCategory(id);
        Assert.assertEquals(true, res);
    }

    @Test(expected = RecordNotFoundException.class)
    public void ShouldNotDeleteCategory() {
        int id = 1;
        Mockito.when(categoryRepository.existsById(Mockito.any())).thenReturn(false);
        categoryService.deleteCategory(id);

    }

    @Test
    public void ShouldGetCategoryById() {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        CategoryDTO res = categoryService.getCategoryById(1);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), categoryDTO.getId());
    }
}
