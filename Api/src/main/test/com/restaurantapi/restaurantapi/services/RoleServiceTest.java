package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.RoleDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    RoleDTO roleDTO = new RoleDTOBuilder()
            .id(1)
            .name("ROLE_ADMIN")
            .build();
    List<RoleDTO> roleDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        roleDTOList.add(roleDTO);
    }

    @Test
    public void shouldGetAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleDTOList.forEach(roleDTO1 -> roleList.add(RoleDTOConvertor.DTOToRole(roleDTO1)));
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
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(RoleDTOConvertor.DTOToRole(roleDTO)));
        RoleDTO res = roleService.getRoleById(1);
        Assert.assertEquals(roleDTO.getId(),res.getId());
    }
}
