package com.restaurantapi.restaurantapi.builder;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.restaurantapi.restaurantapi.dto.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.constraints.Min;
import java.util.List;

public class OrderDTOBuilder extends Builder {
  private WaiterDTO waiter;
  private CustomerDTO customer;
  private double totalPrice;
  private double totalCount;
  private int paymentType;
  private List<OrderItemDTO> orderItems;

  @Override
  public OrderDTO build() {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setId(super.id);
    orderDTO.setTotalPrice(this.totalPrice);
    orderDTO.setTotalCount(this.totalCount);
    orderDTO.setWaiter(this.waiter);
    orderDTO.setCustomer(this.customer);
    orderDTO.setPaymentType(this.paymentType);
    orderDTO.setOrderItems(this.orderItems);
    return orderDTO;
  }

  public OrderDTOBuilder id(int id) {
    super.id = id;
    return this;
  }

  public OrderDTOBuilder waiter(WaiterDTO waiter) {
    this.waiter = waiter;
    return this;
  }

  public OrderDTOBuilder customer(CustomerDTO customer) {
    this.customer = customer;
    return this;
  }

  public OrderDTOBuilder totalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public OrderDTOBuilder totalCount(double totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  public OrderDTOBuilder paymentType(int paymentType) {
    this.paymentType = paymentType;
    return this;
  }

  public OrderDTOBuilder orderItems(List<OrderItemDTO> orderItems) {
    this.orderItems = orderItems;
    return this;
  }
}
