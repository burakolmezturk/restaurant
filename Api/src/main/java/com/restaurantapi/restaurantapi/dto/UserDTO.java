package com.restaurantapi.restaurantapi.dto;


import com.restaurantapi.restaurantapi.entity.BaseEntity;
import com.restaurantapi.restaurantapi.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
public class UserDTO extends BaseDTO {

    @NotEmpty(message = "{USERNAME_IS_EMPTY}")
    @NotNull(message = "{USERNAME_IS_NULL}")
    private String userName;
    @NotEmpty(message = "{PASSWORD_IS_NULL}")
    @NotNull(message = "{PASSWORD_IS_NULL}")
    private String password;
    @Email(message = "{MAIL_FORMAT}")
    private String email;
    private boolean enabled;
    private List<Integer> rolesId;
    private List<Role> rolesList=new ArrayList<>();

}
