package com.restaurantapi.restaurantapi.dto;


import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserDTO extends BaseDTO {


    private String userName;
    private String password;
    private String email;
    private boolean enabled;
    private List<Integer> rolesId;
    private List<Role> rolesList=new ArrayList<>();

}
