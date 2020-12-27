package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);

    List<Role> toEntityList (List<RoleDTO> roleDTOList);
    List<RoleDTO> toDTOList (List<Role> roles);
}
