package com.restaurantapi.restaurantapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDTO {

    @Min(value = 0, message = "{ID_CONTROL}")
    private int id;

}
