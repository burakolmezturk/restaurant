package com.restaurantapi.restaurantapi.entity;


import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@SQLDelete(sql =
        "UPDATE user " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity{

    private String username;
    private String password;
    private boolean enabled;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList = new ArrayList<>();

}
