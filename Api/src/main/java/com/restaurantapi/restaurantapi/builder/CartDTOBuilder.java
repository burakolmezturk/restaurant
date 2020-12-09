package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.CartDTO;

public class CartDTOBuilder extends Builder {

    private int productId;
    private double price;
    private double totalPrice;
    private int piece;
    private int placeId;
    private int tableId;
    private int waiterId;


    @Override
    public CartDTO build() {
       CartDTO cartDTO = new CartDTO();
       cartDTO.setId(super.id);
       cartDTO.setTableId(this.tableId);
       cartDTO.setTotalPrice(this.totalPrice);
       cartDTO.setPlaceId(this.placeId);
       cartDTO.setPiece(this.piece);
       cartDTO.setPrice(this.price);
       cartDTO.setProductId(this.productId);
       cartDTO.setWaiterId(this.waiterId);
       return cartDTO;
    }
    public CartDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public CartDTOBuilder tableId(int tableId){
        this.tableId=tableId;
        return this;
    }
    public CartDTOBuilder placeId(int placeId){
        this.placeId=placeId;
        return this;
    }
    public CartDTOBuilder productId(int productId){
        this.productId=productId;
        return this;
    }
    public CartDTOBuilder waiterId(int waiterId){
        this.waiterId=waiterId;
        return this;
    }
    public CartDTOBuilder piece(int piece){
        this.piece=piece;
        return this;
    }
    public CartDTOBuilder totalPrice(double totalPrice){
        this.totalPrice=totalPrice;
        return this;
    }
    public CartDTOBuilder price(double price){
        this.price=price;
        return this;
    }
}
