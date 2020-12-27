package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Waiter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO categoryDTO);

    List<Category> toEntityList (List<CategoryDTO> categoryDTO);
    List<CategoryDTO> toDTOList (List<Category> category);
}
