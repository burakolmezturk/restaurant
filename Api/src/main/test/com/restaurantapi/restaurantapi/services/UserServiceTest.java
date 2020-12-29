package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.RoleDTOBuilder;
import com.restaurantapi.restaurantapi.builder.UsersDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.UserDTOConverter;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.mapper.RoleMapper;
import com.restaurantapi.restaurantapi.mapper.UserMapper;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Spy
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private List<Integer> roleIds = new ArrayList<>();
    private UserDTO userDTO = new UsersDTOBuilder()
            .userName("burak")
            .email("deneme@")
            .id(1)
            .password(encoder.encode("1234"))
            .enabled(true)
            .build();
    private RoleDTO roleDTO = new RoleDTOBuilder()
            .name("ROLE_WAITER")
            .id(1)
            .build();
    private List<UserDTO> userDTOList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private User user = userMapper.toEntity(userDTO);
    private List<Role> roleList = new ArrayList<>();
    @Before
    public void setUp() {
        roleIds.add(1);
        userDTO.setRolesId(roleIds);
        userDTOList.add(userDTO);
        userList = userMapper.toEntityList(userDTOList);
        roleList.add(roleMapper.toEntity(roleDTO));
    }

    @Test
    public void shouldSaveUser() {
        Mockito.when(roleRepository.findAllById(Mockito.any())).thenReturn(roleList);
        userService.saveUser(userDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).findAllById(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldEditUser() {
        Mockito.when(roleRepository.findAllById(Mockito.any())).thenReturn(roleList);
        userService.updateUser(userDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).findAllById(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void shouldDeleteUser() {
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        userService.deleteUser(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void shouldGetUserById(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        UserDTO res = userService.getUserById(1);
        Assert.assertEquals(userDTO.getId(),res.getId());
    }
    @Test
    public void shouldGetListUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> res = userService.getListUsers();
        Assert.assertEquals(userDTOList.get(0).getId(),res.get(0).getId());
    }
}
