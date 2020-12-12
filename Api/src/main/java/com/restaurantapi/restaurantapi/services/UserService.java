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

    public UserDTO saveUser(UserDTO userDTO) {
        List<Role> roleList = roleRepository.findAllById(userDTO.getRolesId());

        User user = UserDTOConverter.DTOToUsers(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.save(user);
        return userDTO;
    }

    public List<UserDTO> getListUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<Integer> roleId = new ArrayList<>();
        userRepository.findAll().forEach(users -> userDTOList.add(UserDTOConverter.UserToDTO(users)));
//        for (UsersDTO usersDTO: usersDTOList) {
//            roleId=new ArrayList<>();
//            for (Role role:usersDTO.getRolesList()) {
//                roleId.add(role.getId());
//            }
//            usersDTO.setRolesId(roleId);
//        }

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

    public UserDTO updateUser(UserDTO userDTO) {
        List<Role> roleList = roleRepository.findAllById(userDTO.getRolesId());
        User user = UserDTOConverter.DTOToUsers(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.saveAndFlush(user);
        return userDTO;
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public List<RoleDTO> getRoles() {
        List<RoleDTO> roleDTOList = new ArrayList<>();

        roleRepository.findAll().forEach(role -> roleDTOList.add(RoleDTOConvertor.roleToDTO(role)));
        return roleDTOList;
    }
}
