package com.restaurantapi.restaurantapi.dto;
import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends  BaseDTO implements Serializable {

    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
    private String description;
    private MediaDTO image;

}
