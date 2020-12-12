package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.UserDTOConverter;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void saveUser(UserDTO userDTO) {
        List<Role> roleList = roleRepository.findAllById(userDTO.getRolesId());

        User user = UserDTOConverter.DTOToUsers(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.save(user);

    }

    public List<UserDTO> getListUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<Integer> roleId = new ArrayList<>();
        userRepository.findAll().forEach(users -> userDTOList.add(UserDTOConverter.UserToDTO(users)));

        return userDTOList;
    }

    public UserDTO getUserById(int userId) {
        List<Integer> roleId = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            optionalUser.get().getRoleList().forEach(role -> roleId.add(role.getId()));
            UserDTO userDTO = UserDTOConverter.UserToDTO(optionalUser.get());
            userDTO.setRolesId(roleId);
            return userDTO;

        } else {
            return null;
        }


    }

    public void updateUser(UserDTO userDTO) {
        List<Role> roleList = roleRepository.findAllById(userDTO.getRolesId());
        User user = UserDTOConverter.DTOToUsers(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.saveAndFlush(user);

    }

    public void deleteUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            userOptional.get().setRoleList(null);
            userRepository.deleteById(userId);
        }

    }


}
