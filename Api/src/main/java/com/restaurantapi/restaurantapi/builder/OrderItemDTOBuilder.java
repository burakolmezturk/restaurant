package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.OrderItemDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;

public class OrderItemDTOBuilder extends Builder {

  private ProductDTO product;
  private double price;
  private double totalPrice;
  private int piece;


  @Override
  public OrderItemDTO build() {
    OrderItemDTO orderItemDTO = new OrderItemDTO();
    orderItemDTO.setId(super.id);
    orderItemDTO.setTotalPrice(this.totalPrice);
    orderItemDTO.setPiece(this.piece);
    orderItemDTO.setPrice(this.price);
    orderItemDTO.setProduct(this.product);
    return orderItemDTO;
  }

  public OrderItemDTOBuilder id(int id) {
    super.id = id;
    return this;
  }

  public OrderItemDTOBuilder product(ProductDTO productDTO) {
    this.product = productDTO;
    return this;
  }

  public OrderItemDTOBuilder piece(int piece) {
    this.piece = piece;
    return this;
  }

  public OrderItemDTOBuilder totalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public OrderItemDTOBuilder price(double price) {
    this.price = price;
    return this;
  }
}
