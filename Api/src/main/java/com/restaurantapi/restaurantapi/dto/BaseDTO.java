package com.restaurantapi.restaurantapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDTO {
    private int id;

}
