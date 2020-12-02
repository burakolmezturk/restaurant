package com.restaurantapi.restaurantapi.repository;


import com.restaurantapi.restaurantapi.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
}
