package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.UserDTOConverter;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UsersDTO;
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

    public UsersDTO saveUser(UsersDTO usersDTO) {
        List<Role> roleList = roleRepository.findAllById(usersDTO.getRolesId());

        User user = UserDTOConverter.DTOToUsers(usersDTO);
        user.setPassword(encoder.encode(usersDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.save(user);
        return usersDTO;
    }

    public List<UsersDTO> getListUsers() {
        List<UsersDTO> usersDTOList = new ArrayList<>();
        List<Integer> roleId = new ArrayList<>();
        userRepository.findAll().forEach(users -> usersDTOList.add(UserDTOConverter.UserToDTO(users)));
//        for (UsersDTO usersDTO: usersDTOList) {
//            roleId=new ArrayList<>();
//            for (Role role:usersDTO.getRolesList()) {
//                roleId.add(role.getId());
//            }
//            usersDTO.setRolesId(roleId);
//        }

        return usersDTOList;
    }

    public UsersDTO getUserById(int userId) {
        List<Integer> roleId = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            optionalUser.get().getRoleList().forEach(role -> roleId.add(role.getId()));
            UsersDTO usersDTO = UserDTOConverter.UserToDTO(optionalUser.get());
            usersDTO.setRolesId(roleId);
            return usersDTO;

        } else {
            return null;
        }


    }

    public UsersDTO updateUser(UsersDTO usersDTO) {
        List<Role> roleList = roleRepository.findAllById(usersDTO.getRolesId());
        User user = UserDTOConverter.DTOToUsers(usersDTO);
        user.setPassword(encoder.encode(usersDTO.getPassword()));
        user.setRoleList(roleList);
        userRepository.saveAndFlush(user);
        return usersDTO;
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
