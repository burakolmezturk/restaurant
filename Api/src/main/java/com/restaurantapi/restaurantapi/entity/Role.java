package com.restaurantapi.restaurantapi.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@SQLDelete(sql =
        "UPDATE roles " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name="ROLES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Role extends BaseEntity{

    private String name;

}
