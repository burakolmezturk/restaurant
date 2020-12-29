package com.restaurantapi.restaurantapi.dto;


import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO {


    private String name;
    private List<Integer> categoryIdList = new ArrayList<>();
    private int[] category;
    private String description;
    private double salesPrice;
    private double purchasePrice;
    private MediaDTO image;
    private List<CategoryDTO> categories = new ArrayList<>();

}
