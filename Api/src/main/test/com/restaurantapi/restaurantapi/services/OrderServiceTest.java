package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.*;
import com.restaurantapi.restaurantapi.dto.*;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Order;
import com.restaurantapi.restaurantapi.entity.OrderItem;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.mapper.OrderItemMapper;
import com.restaurantapi.restaurantapi.mapper.OrderMapper;
import com.restaurantapi.restaurantapi.repository.OrderItemRepository;
import com.restaurantapi.restaurantapi.repository.OrderRepository;
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
public class OrderServiceTest {

  @InjectMocks private OrderService orderService;

  @Mock private OrderRepository orderRepository;

  @Mock private OrderItemRepository orderItemRepository;

  @Spy private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

  @Spy private OrderItemMapper orderItemMapper = Mappers.getMapper(OrderItemMapper.class);

  @Spy private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

  private List<OrderItemDTO> orderDTOList = new ArrayList<>();
  private List<OrderItem> orders = new ArrayList<>();

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
  private Customer customer = customerMapper.toEntity(customerDTO);

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
    orders = orderItemMapper.toEntityList(orderDTOList);
    order = orderMapper.toEntity(orderDTO);
  }

  @Test
  public void shouldAddOrder() {

    Mockito.when(orderItemMapper.toEntityList(orderDTOList)).thenReturn(orders);
    Mockito.when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
    Mockito.when(orderMapper.toEntity(orderDTO)).thenReturn(order);
    Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);
    Mockito.when(orderItemRepository.save(Mockito.any())).thenReturn(orders);
    orderService.addOrder(orderDTO);
  }
}
