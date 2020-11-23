package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.entity.User;
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
    public List<User> getList() {
        return userService.getListUsers();
    }

    @GetMapping("/function/list/{id}")
    public User getUserById(@PathVariable String username) {
        return userService.getUserById(username);
    }

    @PostMapping("/function/add")
    public User addUser(@RequestBody User user,@RequestParam String role) {
        return userService.saveUser(user,role);
    }

    @PutMapping("/function/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/function/delete/{id}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) {
       return userService.login(username, password);
    }
}


