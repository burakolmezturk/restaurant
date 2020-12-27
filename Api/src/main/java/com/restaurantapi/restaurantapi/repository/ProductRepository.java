package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Slice<Product> findProductByCategories(Category category,Pageable pageable);

}
