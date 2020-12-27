package com.restaurantapi.restaurantapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SQLDelete(sql =
        "UPDATE place " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Place extends BaseEntity{

    private String name;
    private int tableCount;
    @JsonIgnore
    @JoinColumn(name = "media_id")
    @OneToOne
    private Media image;

}
