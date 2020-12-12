package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("Select u From User u WHERE u.username=:username and  u.password=:password")
    public User login(@Param("username") String userName, @Param("password") String password);
    @Query("Select u From User u WHERE u.username=:username")
    public User getUsersByUsername(@Param("username") String userName);
}
