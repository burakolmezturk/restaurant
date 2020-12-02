package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,String> {

    @Query("Select u From Users u WHERE u.username=:username and u.password=:password")
    public Users login(String username, String password);
}
