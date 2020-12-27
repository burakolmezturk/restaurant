package com.restaurantapi.restaurantapi.exception;


import com.restaurantapi.restaurantapi.dto.ErrorMessage;

public class RecordNotFoundException extends RuntimeException {
    private int errorCode;

    public RecordNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.toString());
        this.errorCode=errorMessage.value();
    }
    public int getErrorCode(){return this.errorCode;}
}
