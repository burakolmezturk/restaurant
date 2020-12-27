package com.restaurantapi.restaurantapi.dto;
import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends BaseEntity {


    private String name;
    private String description;
    private MediaDTO image;

}
