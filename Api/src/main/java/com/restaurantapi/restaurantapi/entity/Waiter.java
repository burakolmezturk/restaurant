package com.restaurantapi.restaurantapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Data
@Entity
@SQLDelete(sql =
        "UPDATE waiter " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Waiter extends  BaseEntity{

    private String name;
    private String email;
    private int age;
    private String phone;
    @JsonIgnore
    @JoinColumn(name = "media_id")
    @OneToOne
    private Media image;

}
