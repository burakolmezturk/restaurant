package com.restaurantapi.restaurantapi.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@SQLDelete(sql = "UPDATE orders" + "SET deleted = true " + "WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "orders")
public class Order extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "waiter_id")
  private Waiter waiter;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  private double totalPrice;
  private double totalCount;
  private int paymentType;
}
