package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.RoleDTOBuilder;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import com.restaurantapi.restaurantapi.services.RoleService;
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

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {
    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

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
    public void shouldGetRoles(){
        Mockito.when(roleService.getAllRoles()).thenReturn(roleDTOList);
        List<RoleDTO> res = roleController.getRoles();
        Assert.assertEquals(roleDTOList.get(0).getId(),res.get(0).getId());
    }
    @Test
    public void shouldAddRole(){

        roleController.addRole(roleDTO);
        Mockito.verify(roleService, Mockito.times(1)).addRole(Mockito.any());
    }
    @Test
    public void shouldEditRole(){

        roleController.editRole(roleDTO);
        Mockito.verify(roleService, Mockito.times(1)).editRole(Mockito.any());
    }
    @Test
    public void shouldDeleteRoleRole(){

        roleController.deleteRole(roleDTO);
        Mockito.verify(roleService, Mockito.times(1)).deleteRole(Mockito.any());
    }
    @Test
    public void shouldGetRoleById(){

        Mockito.when(roleService.getRoleById(1)).thenReturn(roleDTO);
        RoleDTO res = roleController.getRoleById(roleDTO.getId());
        Assert.assertEquals(roleDTO.getId(),res.getId());
    }
}
