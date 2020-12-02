package com.restaurantapi.restaurantapi.repository;


import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Integer> {
}
