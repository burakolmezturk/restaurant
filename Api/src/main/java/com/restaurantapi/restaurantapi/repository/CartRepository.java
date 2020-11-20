package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Cart;
import com.restaurantapi.restaurantapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("Select DISTINCT category From Product ")
    public List<String> getAllCategory();
    @Query("SELECT p from Product p Where p.category=:categoryName")
    public List<Product> findCategoryByName(String categoryName);
}
