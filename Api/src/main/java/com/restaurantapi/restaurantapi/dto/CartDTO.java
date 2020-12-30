package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CartDTO extends BaseDTO {

    @NotEmpty(message = "{CATEGORY_NOT_FOUND}")
    @NotNull(message = "{CATEGORY_NOT_FOUND}")
    private int productId;
    @Min(value = 0, message = "{MIN_PRICE}")
    private double price;
    @Min(value = 0, message = "{MIN_PRICE}")
    private double totalPrice;
    @Min(value = 0, message = "{MIN_PIECE}")
    private int piece;
    private int placeId;
    private int tableId;
    private int waiterId;
    private int customerId;

}
