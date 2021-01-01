package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Waiter;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class OrderDTO extends BaseDTO {

  private WaiterDTO waiter;
  private CustomerDTO customer;

  @Min(value = 0, message = "{MIN_PRICE}")
  private double totalPrice;

  @Min(value = 0, message = "{MIN_PIECE}")
  private double totalCount;

  private int paymentType;
  private List<OrderItemDTO> orderItems;
}
