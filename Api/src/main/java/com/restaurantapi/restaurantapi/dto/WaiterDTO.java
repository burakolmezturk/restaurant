package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class WaiterDTO extends BaseDTO {

    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
    @Email(message = "{MAIL_FORMAT}")
    private String email;
    @Min(value = 18, message = "{MIN_AGE_CONTROL}")
    @Max(value = 150, message = "{MAX_AGE_CONTROL}")
    private int age;
    private String phone;
    private MediaDTO image;


}
