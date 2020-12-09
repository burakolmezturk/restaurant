package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.CartDTO;
import com.restaurantapi.restaurantapi.entity.Cart;

public class CartDTOConvertor {
    private CartDTOConvertor() {
    }

    public static Cart dtoToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setPiece(cartDTO.getPiece());
        cart.setPlaceId(cartDTO.getPlaceId());
        cart.setPrice(cartDTO.getPrice());
        cart.setProductId(cartDTO.getProductId());
        cart.setTableId(cartDTO.getTableId());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setWaiterId(cartDTO.getWaiterId());
        return cart;
    }


}
