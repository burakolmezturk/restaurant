package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Authorities;
import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.repository.AuthorityRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user,String role) {
        Authorities authorities=new Authorities();
        authorities.setUsername(user.getUsername());
        authorities.setAuthority("ROLE_"+role);
        authorityRepository.save(authorities);

        return userRepository.save(user);
    }

    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String username) {
        return userRepository.findById(username).get();
    }

    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public User login(String username, String password) {
        User user = userRepository.login(username, password);
        Authorities authorities = authorityRepository.getRole(username);
        return user;
    }
}
