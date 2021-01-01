package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.OrderDTO;
import com.restaurantapi.restaurantapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
  @Autowired OrderService orderService;

  @PostMapping
  public boolean addOrder(@RequestBody @Valid OrderDTO orderDTO) {
    return orderService.addOrder(orderDTO);
  }
}
