package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

@Data
public class WaiterDTO extends BaseEntity {

    private String name;
    private String email;
    private int age;
    private String phone;
    private MediaDTO image;

}
