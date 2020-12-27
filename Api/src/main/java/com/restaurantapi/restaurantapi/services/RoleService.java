package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.RoleMapper;
import com.restaurantapi.restaurantapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  RoleMapper roleMapper;

    public List<RoleDTO> getAllRoles(){

        List<Role>roleList =roleRepository.findAll();
        if(roleList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return   roleMapper.toDTOList(roleList);
    }

    public void addRole(RoleDTO roleDTO){
        if(roleDTO==null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        roleRepository.save(roleMapper.toEntity(roleDTO));
    }

    public void editRole(RoleDTO roleDTO){
        if(roleDTO==null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        roleRepository.saveAndFlush(roleMapper.toEntity(roleDTO));
    }

    public void deleteRole(RoleDTO roleDTO){
        if(roleDTO==null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        roleRepository.deleteById(roleMapper.toEntity(roleDTO).getId());
    }

    public RoleDTO getRoleById(int roleId){

       Optional<Role> role = roleRepository.findById(roleId);
       if(!role.isPresent()) throw new BusinessRuleException(ErrorMessage.ROLE_NOT_FOUND);

       return  roleMapper.toDTO(role.get());
    }
}
