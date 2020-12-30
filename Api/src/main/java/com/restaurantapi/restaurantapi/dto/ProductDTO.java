package com.restaurantapi.restaurantapi.dto;


import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO {

    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
    private List<Integer> categoryIdList = new ArrayList<>();
    private int[] category;
    private String description;
    @Min(value = 0, message = "{MIN_PRICE}")
    private double salesPrice;
    @Min(value = 0, message = "{MIN_PRICE}")
    private double purchasePrice;
    private MediaDTO image;
    @NotNull(message = "{CATEGORY_NOT_FOUND}")
    @NotEmpty(message = "{CATEGORY_NOT_FOUND}")
    private List<CategoryDTO> categories = new ArrayList<>();

}
