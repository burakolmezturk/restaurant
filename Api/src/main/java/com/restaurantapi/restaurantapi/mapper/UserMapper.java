package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "userName")
    UserDTO toDTO(User user);

    @Mapping(source = "userName", target = "username")
    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> user);
    List<User> toEntityList(List<UserDTO> userDTO);
}
