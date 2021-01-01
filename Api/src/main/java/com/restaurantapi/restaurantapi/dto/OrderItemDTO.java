package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.Order;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemDTO extends BaseDTO {

  @NotEmpty(message = "{PRODUCT_NOT_FOUND}")
  @NotNull(message = "{PRODUCT_NOT_FOUND}")
  private ProductDTO product;

  @Min(value = 0, message = "{MIN_PRICE}")
  private double price;

  @Min(value = 0, message = "{MIN_PRICE}")
  private double totalPrice;

  @Min(value = 0, message = "{MIN_PIECE}")
  private int piece;

  private Order order;
}
