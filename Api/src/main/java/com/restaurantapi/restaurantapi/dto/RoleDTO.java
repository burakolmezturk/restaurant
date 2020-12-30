package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RoleDTO extends BaseDTO {

    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
}
