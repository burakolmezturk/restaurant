package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.*;
import com.restaurantapi.restaurantapi.dto.*;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Order;
import com.restaurantapi.restaurantapi.entity.OrderItem;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.mapper.OrderItemMapper;
import com.restaurantapi.restaurantapi.mapper.OrderMapper;
import com.restaurantapi.restaurantapi.repository.OrderItemRepository;
import com.restaurantapi.restaurantapi.repository.OrderRepository;
import com.restaurantapi.restaurantapi.services.OrderService;
import com.restaurantapi.restaurantapi.services.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
  @InjectMocks private OrderController orderController;

  @Mock private OrderService orderService;


  private List<OrderItemDTO> orderDTOList = new ArrayList<>();

  private ProductDTO productDTO =
          new ProductDTOBuilder()
                  .name("Deneme")
                  .id(1)
                  .description("Deneme")
                  .salesPrice(8)
                  .purchasePrice(3)
                  .categoryId(new int[1])
                  .build();

  private MediaDTO mediaDTO =
          new MediaDTOBuilder().fileContent(null).fileName("deneme").id(1).build();

  private CustomerDTO customerDTO =
          new CustomerDTOBuilder()
                  .address("deneme")
                  .id(1)
                  .name("ahmet")
                  .phone("000000")
                  .surname("mehemet")
                  .image(mediaDTO)
                  .build();

  private OrderDTO orderDTO =
          new OrderDTOBuilder().id(1).totalCount(5).totalPrice(15).paymentType(1).customer(customerDTO).build();

  private OrderItemDTO orderItemDTO =
          new OrderItemDTOBuilder().id(1).piece(2).price(10).totalPrice(20).product(productDTO).build();

  private Order order = new Order();

  private OrderItem orderItem = new OrderItem();
  @Before
  public void setUp() {
    orderDTOList.add(orderItemDTO);
    orderDTO.setOrderItems(orderDTOList);

  }

  @Test
  public void shouldAdd() {
    Mockito.when(orderService.addOrder(Mockito.any())).thenReturn(true);
    orderController.addOrder(orderDTO);
  }

}
