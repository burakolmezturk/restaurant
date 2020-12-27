package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDTOList (List<Product> products);
    List<Product> toEntityList (List<ProductDTO> productDTOS);
}
