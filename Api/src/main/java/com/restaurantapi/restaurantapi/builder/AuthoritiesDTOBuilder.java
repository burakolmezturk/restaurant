package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.AuthoritiesDTO;

public class AuthoritiesDTOBuilder  extends Builder{
 

    private String username;
    private String authority;

    @Override
    public AuthoritiesDTO build() {
        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO();
        authoritiesDTO.setAuthority(this.authority);
        authoritiesDTO.setId(super.id);
        authoritiesDTO.setUsername(this.username);
        return authoritiesDTO;
    }
    public AuthoritiesDTOBuilder username(String username){
        this.username=username;
        return this ;
    }
    public AuthoritiesDTOBuilder authority(String authority){
        this.authority=authority;
        return this;
    }
    public AuthoritiesDTOBuilder id(int id){
        super.id=id;
        return this;
    }
}
