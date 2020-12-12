package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.UserDTO;

public class UsersDTOBuilder extends Builder{
    private String userName;
    private String password;
    private String email;
    private boolean enabled;

    @Override
    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(super.id);
        userDTO.setUserName(this.userName);
        userDTO.setPassword(this.password);
        userDTO.setEmail(this.email);
        userDTO.setEnabled(this.enabled);
        return userDTO;
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
