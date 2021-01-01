package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {}
