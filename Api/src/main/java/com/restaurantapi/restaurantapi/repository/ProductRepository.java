package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
