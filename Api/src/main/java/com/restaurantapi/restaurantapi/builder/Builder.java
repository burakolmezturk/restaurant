package com.restaurantapi.restaurantapi.builder;


import com.restaurantapi.restaurantapi.entity.Media;

public abstract class Builder<T> {
    public int id;

    public abstract T build();
}
