package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
