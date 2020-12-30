package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.RoleMapper;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleDTO> getAllRoles() {

        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return roleMapper.toDTOList(roleList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(RoleDTO roleDTO) {

        roleRepository.save(roleMapper.toEntity(roleDTO));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editRole(RoleDTO roleDTO) {

        roleRepository.saveAndFlush(roleMapper.toEntity(roleDTO));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(RoleDTO roleDTO) {

        roleRepository.deleteById(roleMapper.toEntity(roleDTO).getId());
    }

    public RoleDTO getRoleById(int roleId) {

        Optional<Role> role = roleRepository.findById(roleId);
        if (!role.isPresent()) throw new BusinessRuleException(ErrorMessage.ROLE_NOT_FOUND);

        return roleMapper.toDTO(role.get());
    }
}
