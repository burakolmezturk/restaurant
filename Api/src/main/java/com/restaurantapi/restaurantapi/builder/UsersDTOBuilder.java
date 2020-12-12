package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.UsersDTO;

public class UsersDTOBuilder extends Builder{
    private String userName;
    private String password;
    private String email;
    private boolean enabled;

    @Override
    public UsersDTO build() {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(super.id);
        usersDTO.setUserName(this.userName);
        usersDTO.setPassword(this.password);
        usersDTO.setEmail(this.email);
        usersDTO.setEnabled(this.enabled);
        return usersDTO;
    }
    public UsersDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public UsersDTOBuilder userName(String userName){
        this.userName=userName;
        return this;
    }
    public UsersDTOBuilder password(String password){
        this.password=password;
        return this;
    }
    public UsersDTOBuilder email(String email){
        this.email=email;
        return this;
    }
    public UsersDTOBuilder enabled(boolean enabled){
        this.enabled=enabled;
        return this;
    }
}
