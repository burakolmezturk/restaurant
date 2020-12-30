package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.RoleDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.mapper.RoleMapper;
import com.restaurantapi.restaurantapi.mapper.RoleMapperImpl;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Spy
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleDTO = new RoleDTOBuilder()
            .id(1)
            .name("ROLE_ADMIN")
            .build();
    List<RoleDTO> roleDTOList = new ArrayList<>();
    List<Role> roleList = new ArrayList<>();
    Role role = new Role();

    @Before
    public void setUp() {
        roleDTOList.add(roleDTO);
        roleList = roleMapper.toEntityList(roleDTOList);
        role = roleMapper.toEntity(roleDTO);
    }

    @Test
    public void shouldGetAllRoles() {
        Mockito.when(roleRepository.findAll()).thenReturn(roleList);
        List<RoleDTO> res = roleService.getAllRoles();

        Assert.assertEquals(roleDTOList.get(0).getId(), res.get(0).getId());
    }

    @Test
    public void shouldAddRole() {

        roleService.addRole(roleDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    public void shouldNotEditRole() {
        roleService.editRole(roleDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void shouldDeleteRole() {
        roleService.deleteRole(roleDTO);
        Mockito.verify(roleRepository, Mockito.times(1)).deleteById(roleDTO.getId());
    }

    @Test
    public void shouldGetRoleById() {
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        RoleDTO res = roleService.getRoleById(1);
        Assert.assertEquals(roleDTO.getId(), res.getId());
    }
}
