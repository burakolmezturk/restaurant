package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.RoleDTO;

public class RoleDTOBuilder extends Builder{
 

    private String name;

    @Override
    public RoleDTO build() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(super.id);
        roleDTO.setName(this.name);
        return roleDTO;
    }
    public RoleDTOBuilder name(String name){
        this.name=name;
        return this ;
    }

    public RoleDTOBuilder id(int id){
        super.id=id;
        return this;
    }
}
