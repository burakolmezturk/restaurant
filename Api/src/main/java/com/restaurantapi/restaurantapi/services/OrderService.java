package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.OrderDTO;
import com.restaurantapi.restaurantapi.dto.OrderItemDTO;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Order;
import com.restaurantapi.restaurantapi.entity.OrderItem;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.mapper.OrderItemMapper;
import com.restaurantapi.restaurantapi.mapper.OrderMapper;
import com.restaurantapi.restaurantapi.repository.CustomerRepository;
import com.restaurantapi.restaurantapi.repository.OrderItemRepository;
import com.restaurantapi.restaurantapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private OrderItemRepository orderItemRepository;

  @Autowired private OrderMapper orderMapper;

  @Autowired private OrderItemMapper orderItemMapper;

  @Autowired private CustomerMapper customerMapper;

  @Autowired private CustomerRepository customerRepository;

  @Transactional(propagation = Propagation.REQUIRED)
  public boolean addOrder(OrderDTO orderDTO) {

    List<OrderItemDTO> orderDTOItems = orderDTO.getOrderItems();
    if (orderDTOItems.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    List<OrderItem> orders = orderItemMapper.toEntityList(orderDTOItems);

    Order order = orderMapper.toEntity(orderDTO);
    order.setCustomer(prepareCustomer(orderDTO.getCustomer()));

    prepareOrderData(orders, order);

    orderRepository.save(order);

    orderItemRepository.saveAll(orders);

    return true;
  }

  private void prepareOrderData(List<OrderItem> orders, Order order) {
    double totalPrice = 0;
    int totalCount = 0;

    for (int i = 0; i < orders.size(); i++) {
      OrderItem orderItem = orders.get(i);

      orderItem.setOrder(order);
      totalPrice += orderItem.getTotalPrice();
      totalCount += orderItem.getPiece();
    }
    order.setTotalCount(totalCount);
    order.setTotalPrice(totalPrice);
  }

  private Customer prepareCustomer(CustomerDTO customerDTO) {

    if (customerDTO != null) {
      return customerMapper.toEntity(customerDTO);
    }

    Optional<Customer> optional = customerRepository.findById(1);
    if (!optional.isPresent()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    return optional.get();
  }
}
