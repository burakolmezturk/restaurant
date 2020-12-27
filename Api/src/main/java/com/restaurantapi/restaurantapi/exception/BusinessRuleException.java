package com.restaurantapi.restaurantapi.exception;

import com.restaurantapi.restaurantapi.dto.ErrorMessage;

public class BusinessRuleException extends RuntimeException{
    private int errorCode;
    public BusinessRuleException(ErrorMessage errorMessage){
        super(errorMessage.toString());
        this.errorCode=errorMessage.value();
    }
    public int getErrorCode(){return this.errorCode;}
}
