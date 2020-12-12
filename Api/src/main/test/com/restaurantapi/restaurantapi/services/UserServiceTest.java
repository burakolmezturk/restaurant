package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.UsersDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.UserDTOConverter;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

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
    public void shouldSaveUser() {
        userService.saveUser(userDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).findAllById(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldEditUser() {
        userService.updateUser(userDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).findAllById(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void shouldDeleteUser() {
        userService.deleteUser(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void shouldGetUserById(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(UserDTOConverter.DTOToUsers(userDTO)));
        UserDTO res = userService.getUserById(1);
        Assert.assertEquals(userDTO.getId(),res.getId());
    }
    @Test
    public void shouldGetListUsers(){
        List<User> userList = new ArrayList<>();
        userDTOList.forEach(userDTO1 -> userList.add(UserDTOConverter.DTOToUsers(userDTO1)));
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> res = userService.getListUsers();
        Assert.assertEquals(userDTOList.get(0).getId(),res.get(0).getId());
    }
}
