package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Authorities;
import com.restaurantapi.restaurantapi.entity.Users;
import com.restaurantapi.restaurantapi.repository.AuthorityRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users users, String role) {
        Authorities authorities = new Authorities();
        authorities.setUsername(users.getUsername());
        authorities.setAuthority("ROLE_" + role);
        authorityRepository.save(authorities);

        return userRepository.save(users);
    }

    public List<Users> getListUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(String username) {
        Optional<Users> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }

    }

    public Users updateUser(Users users) {
        return userRepository.saveAndFlush(users);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public Users login(String username, String password) {

        return userRepository.login(username, password);
    }
}
