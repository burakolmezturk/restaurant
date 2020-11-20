package com.restaurantapi.restaurantapi.entity;



import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private int productId;
    private String price;
    private String totalPrice;
    private int piece;
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }



}
