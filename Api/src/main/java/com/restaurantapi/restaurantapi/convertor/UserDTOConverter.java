package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.User;


public class UserDTOConverter {
    public static UserDTO UserToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setEmail(user.getEmail());
        userDTO.setRolesList(user.getRoleList());
        return userDTO;
    }
    public static User DTOToUsers(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.isEnabled());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
