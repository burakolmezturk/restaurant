package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.OrderItemDTO;
import com.restaurantapi.restaurantapi.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toEntity (OrderItemDTO orderItemDTO);
    OrderItemDTO toDTO(OrderItem orderItem);

    List<OrderItem> toEntityList (List<OrderItemDTO> orderItemDTOList);
    List<OrderItemDTO> toDTOList (List<OrderItem> orderItems);
}
