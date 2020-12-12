package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.UsersDTO;
import com.restaurantapi.restaurantapi.entity.User;


public class UserDTOConverter {
    public static UsersDTO UserToDTO(User user){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        usersDTO.setUserName(user.getUsername());
        usersDTO.setPassword(user.getPassword());
        usersDTO.setEnabled(user.isEnabled());
        usersDTO.setRolesList(user.getRoleList());
        return usersDTO;
    }
    public static User DTOToUsers(UsersDTO usersDTO){
        User user = new User();
        user.setId(usersDTO.getId());
        user.setUsername(usersDTO.getUserName());
        user.setPassword(usersDTO.getPassword());
        user.setEnabled(usersDTO.isEnabled());
        return user;
    }
}
