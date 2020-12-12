package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("Select a From Role a WHERE a.name=:username ")
    public Role getRole(String username);
}
