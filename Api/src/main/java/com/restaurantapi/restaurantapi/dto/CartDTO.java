package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

@Data
public class CartDTO extends BaseDTO {

    private int productId;
    private double price;
    private double totalPrice;
    private int piece;
    private int placeId;
    private int tableId;
    private int waiterId;
    private int customerId;

}
