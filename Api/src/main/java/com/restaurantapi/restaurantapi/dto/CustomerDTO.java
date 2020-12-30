package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerDTO extends BaseDTO {

    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
    private String surname;
    @NotNull(message = "{ADDRESS_IS_NULL}")
    @NotEmpty(message = "{ADDRESS_IS_EMPTY}")
    private String address;
    private String phone;
    private MediaDTO image;
}
