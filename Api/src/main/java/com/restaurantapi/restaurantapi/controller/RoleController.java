package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(value = "*")
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<RoleDTO> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public void addRole(@Valid @RequestBody @NotNull(message = "{ROLE_NOT_FOUND}") RoleDTO roleDTO) {
        roleService.addRole(roleDTO);
    }

    @PutMapping("/edit")
    public void editRole(@Valid @RequestBody @NotNull(message = "{ROLE_NOT_FOUND}") RoleDTO roleDTO) {
        roleService.editRole(roleDTO);
    }

    @DeleteMapping("/delete")
    public void deleteRole(@Valid @RequestBody @NotNull(message = "{ROLE_NOT_FOUND}") RoleDTO roleDTO) {
        roleService.deleteRole(roleDTO);
    }

    @GetMapping("/{roleId}")
    public RoleDTO getRoleById(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int roleId) {
        return roleService.getRoleById(roleId);
    }
}
