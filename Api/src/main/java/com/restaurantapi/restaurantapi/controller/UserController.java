package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/function/list")
    public List<UserDTO> getList() {
        return userService.getListUsers();
    }

    @GetMapping("/function/list/{id}")
    public UserDTO getUserById(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/function/add")
    public void addUser(@Valid @RequestBody @NotNull(message = "{USER_NOT_FOUND}") UserDTO users) {
        userService.saveUser(users);
    }

    @PutMapping("/function/update")
    public void updateUser(@Valid @RequestBody @NotNull(message = "{USER_NOT_FOUND}") UserDTO users) {
        userService.updateUser(users);
    }

    @DeleteMapping("/function/delete/{id}")
    public void deleteUser(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/login")
    public void login() {
    }

}


