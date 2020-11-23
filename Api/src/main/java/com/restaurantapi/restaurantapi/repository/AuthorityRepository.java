package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Authorities;
import com.restaurantapi.restaurantapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authorities,Integer> {
    @Query("Select a From Authorities a WHERE a.username=:username ")
    public Authorities getRole(String username);
}
