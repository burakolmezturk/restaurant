package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query("Select u From Users u WHERE u.username=:username and u.password=:password")
    public User login(String username,String password);
}
