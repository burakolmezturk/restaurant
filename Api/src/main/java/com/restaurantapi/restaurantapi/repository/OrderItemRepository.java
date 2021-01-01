package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {}
