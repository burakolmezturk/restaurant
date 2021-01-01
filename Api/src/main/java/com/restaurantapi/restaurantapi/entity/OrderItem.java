package com.restaurantapi.restaurantapi.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE order_item" + "SET deleted = true " + "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int piece;
  private double price;
  private double totalPrice;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
}
