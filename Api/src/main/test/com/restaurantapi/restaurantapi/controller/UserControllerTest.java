package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.RoleDTOBuilder;
import com.restaurantapi.restaurantapi.builder.UsersDTOBuilder;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.services.RoleService;
import com.restaurantapi.restaurantapi.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private UserDTO userDTO = new UsersDTOBuilder()
            .userName("burak")
            .email("deneme@")
            .id(1)
            .password(encoder.encode("1234"))
            .enabled(true)
            .build();
    private List<UserDTO> userDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        userDTOList.add(userDTO);
    }

    @Test
    public void shouldGetUsers() {
        Mockito.when(userService.getListUsers()).thenReturn(userDTOList);
        List<UserDTO> res = userController.getList();
        Assert.assertEquals(userDTOList.get(0).getId(), res.get(0).getId());
    }

    @Test
    public void shouldAddUser() {

        userController.addUser(userDTO);
        Mockito.verify(userService, Mockito.times(1)).saveUser(Mockito.any());
    }

    @Test
    public void shouldEditUser() {

        userController.updateUser(userDTO);
        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.any());
    }

    @Test
    public void shouldDeleteUser() {

        userController.deleteUser(1);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(1);
    }

    @Test
    public void shouldGetUserById() {

        Mockito.when(userService.getUserById(1)).thenReturn(userDTO);
        UserDTO res = userController.getUserById(userDTO.getId());
        Assert.assertEquals(userDTO.getId(), res.getId());
    }
}
