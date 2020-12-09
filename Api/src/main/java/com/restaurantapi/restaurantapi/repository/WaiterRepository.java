package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter,Integer> {
}
