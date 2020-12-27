package com.restaurantapi.restaurantapi.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@SQLDelete(sql =
        "UPDATE media " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media extends BaseEntity {

    @Column(length = 100000)
    private byte[] fileContent;
    private String fileName;
    
}
