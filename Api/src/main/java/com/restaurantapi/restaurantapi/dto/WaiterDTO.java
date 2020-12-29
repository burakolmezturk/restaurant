package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

@Data
public class WaiterDTO extends BaseDTO {

    private String name;
    private String email;
    private int age;
    private String phone;
    private MediaDTO image;

}
