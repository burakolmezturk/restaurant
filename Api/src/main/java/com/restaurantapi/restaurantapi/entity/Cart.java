package com.restaurantapi.restaurantapi.entity;


import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@SQLDelete(sql =
        "UPDATE cart" +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cart extends BaseEntity {

    private int productId;
    private double totalPrice;
    private int piece;
    private int placeId;
    private double price;
    private int tableId;
    private int waiterId;
    private int customerId ;
}
