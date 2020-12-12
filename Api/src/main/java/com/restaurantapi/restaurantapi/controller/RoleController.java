package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(value = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<RoleDTO> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public void addRole(@RequestBody RoleDTO roleDTO) {
        roleService.addRole(roleDTO);
    }

    @PutMapping("/edit")
    public void editRole(@RequestBody RoleDTO roleDTO) {
        roleService.editRole(roleDTO);
    }

    @DeleteMapping("/delete")
    public void deleteRole(@RequestBody RoleDTO roleDTO) {
        roleService.deleteRole(roleDTO);
    }

    @GetMapping("/{roleId}")
    public RoleDTO getRoleById(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }
}
