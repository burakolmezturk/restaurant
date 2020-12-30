package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.UserDTOConverter;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.dto.UserDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.entity.User;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.UserMapper;
import com.restaurantapi.restaurantapi.mapper.WaiterMapper;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import com.restaurantapi.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired private RoleRepository roleRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private UserMapper userMapper;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Transactional(propagation = Propagation.REQUIRED)
  public void saveUser(UserDTO userDTO) {

    List<Role> roleList = roleRepository.findAllById(userDTO.getRolesId());
    if (roleList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    User user = userMapper.toEntity(userDTO);
    user.setPassword(encoder.encode(userDTO.getPassword()));
    user.setRoleList(roleList);

    userRepository.save(user);
  }

  public List<UserDTO> getListUsers() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    return userMapper.toDTOList(users);
  }

  public UserDTO getUserById(int userId) {
    List<Integer> roleId = new ArrayList<>();
    Optional<User> optionalUser = userRepository.findById(userId);

    if (!optionalUser.isPresent()) throw new RecordNotFoundException(ErrorMessage.USER_NOT_FOUND);
    User user = optionalUser.get();

    user.getRoleList().forEach(role -> roleId.add(role.getId()));

    UserDTO userDTO = userMapper.toDTO(user);
    userDTO.setRolesId(roleId);
    return userDTO;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void updateUser(UserDTO userDTO) {
    List<Integer> rolesIdsList = userDTO.getRolesId();

    if (rolesIdsList.isEmpty()) throw new BusinessRuleException(ErrorMessage.ROLE_NOT_FOUND);

    List<Role> roleList = roleRepository.findAllById(rolesIdsList);
    if (roleList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    User user = userMapper.toEntity(userDTO);
    user.setPassword(encoder.encode(userDTO.getPassword()));
    user.setRoleList(roleList);
    userRepository.saveAndFlush(user);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void deleteUser(int userId) {

    Optional<User> userOptional = userRepository.findById(userId);
    if (!userOptional.isPresent()) throw new BusinessRuleException(ErrorMessage.USER_NOT_FOUND);

    userOptional.get().setRoleList(null);
    userRepository.deleteById(userId);
  }
}
