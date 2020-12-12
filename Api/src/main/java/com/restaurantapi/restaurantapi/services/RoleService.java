package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.RoleDTOConvertor;
import com.restaurantapi.restaurantapi.dto.RoleDTO;
import com.restaurantapi.restaurantapi.entity.Role;
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

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOList =new ArrayList<>();
        roleRepository.findAll().forEach(role -> roleDTOList.add(RoleDTOConvertor.roleToDTO(role)));
        return roleDTOList;
    }
    public void addRole(RoleDTO roleDTO){
        roleRepository.save(RoleDTOConvertor.DTOToRole(roleDTO));
    }
    public void editRole(RoleDTO roleDTO){
        roleRepository.saveAndFlush(RoleDTOConvertor.DTOToRole(roleDTO));
    }
    public void deleteRole(RoleDTO roleDTO){
        roleRepository.deleteById(RoleDTOConvertor.DTOToRole(roleDTO).getId());
    }
    public RoleDTO getRoleById(int roleId){
       Optional<Role> role = roleRepository.findById(roleId);
       if(!role.isPresent()){
           return null;
       }
       return RoleDTOConvertor.roleToDTO(role.get());

    }
}
