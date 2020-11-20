package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
