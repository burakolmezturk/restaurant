package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.OrderDTO;
import com.restaurantapi.restaurantapi.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity (OrderDTO orderDTO);
    OrderDTO toDTO(Order order);

    List<Order> toEntityList (List<OrderDTO> orderDTOList);
    List<OrderDTO> toDTOList (List<Order> orderList);
}
