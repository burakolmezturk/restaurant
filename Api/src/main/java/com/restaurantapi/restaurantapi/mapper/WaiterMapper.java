package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.entity.Waiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WaiterMapper {

    WaiterDTO toDTO(Waiter waiter);
    Waiter toEntity(WaiterDTO waiterDTO);

    List<WaiterDTO> toEntityList (List<Waiter> waiters);
    List<Waiter> toDTOList (List<WaiterDTO> waiterDTOList);

}
