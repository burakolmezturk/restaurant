package com.restaurantapi.restaurantapi.exception;


import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class RecordNotFoundException extends RuntimeException {
    @Autowired
    private MessageSource messageSource;

    private int errorCode;

    public RecordNotFoundException(ErrorMessage errorMessage
    ) {
        super(errorMessage.toString());
        this.errorCode=errorMessage.value();
    }
    public int getErrorCode(){return this.errorCode;}

}
