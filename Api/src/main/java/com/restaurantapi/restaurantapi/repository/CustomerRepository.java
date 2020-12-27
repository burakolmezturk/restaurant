package com.restaurantapi.restaurantapi.repository;

import com.restaurantapi.restaurantapi.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Page<Customer> findCustomersByNameContains(String customerName, Pageable pageable);
}
