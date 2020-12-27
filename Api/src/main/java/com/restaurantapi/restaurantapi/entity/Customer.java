package com.restaurantapi.restaurantapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(sql =
        "UPDATE customer " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity {

    private String name;
    private String surname;
    private String address;
    private String phone;
    @JsonIgnore
    @JoinColumn(name = "media_id")
    @OneToOne
    private Media image;

}
