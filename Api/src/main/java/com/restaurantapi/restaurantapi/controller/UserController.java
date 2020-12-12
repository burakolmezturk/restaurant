package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UsersDTO;
import com.restaurantapi.restaurantapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @GetMapping("/function/list")
    public List<UsersDTO> getList() {
        return userService.getListUsers();
    }

    @GetMapping("/function/list/{id}")
    public UsersDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/function/add")
    public UsersDTO addUser(@RequestBody UsersDTO users) {
        return userService.saveUser(users);
    }

    @PutMapping("/function/update")
    public UsersDTO updateUser(@RequestBody UsersDTO users) {
        return userService.updateUser(users);
    }
    @DeleteMapping("/function/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/login")
    public void login() {
    }
    @GetMapping("/roles")
    public List<RoleDTO> getRoles() {
        return userService.getRoles();
    }
}


