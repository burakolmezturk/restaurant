package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
