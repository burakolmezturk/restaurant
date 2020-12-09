package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Integer> {
}
