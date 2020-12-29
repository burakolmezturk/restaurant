package com.restaurantapi.restaurantapi.exception;

import com.restaurantapi.restaurantapi.dto.ErrorMessage;

import java.util.Locale;

public class SystemException extends RuntimeException{
    private int errorCode;
    public SystemException(ErrorMessage e){
        super(e.toString());
        this.errorCode=e.value();
    }
    public int getErrorCode(){return this.errorCode;}
}
