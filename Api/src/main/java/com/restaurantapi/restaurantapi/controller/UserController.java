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

    @GetMapping("/list")
    public List<User> getList() {
        return userService.getListUsers();
    }

    @GetMapping("/list/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
