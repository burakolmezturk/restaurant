package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.Users;
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
    public List<Users> getList() {
        return userService.getListUsers();
    }

    @GetMapping("/function/list/{id}")
    public Users getUserById(@PathVariable String username) {
        return userService.getUserById(username);
    }

    @PostMapping("/function/add")
    public Users addUser(@RequestBody Users users, @RequestParam String role) {
        return userService.saveUser(users, role);
    }

    @PutMapping("/function/update")
    public Users updateUser(@RequestBody Users users) {
        return userService.updateUser(users);
    }

    @DeleteMapping("/function/delete/{id}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/login")
    public Users login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }
}


