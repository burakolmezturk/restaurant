package com.restaurantapi.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;


public enum ErrorMessage {
    ID_IS_NULL("ID_IS_NULL", 1),
    ENTITY_IS_NULL("ENTITY_IS_NULL", 2),
    RECORD_NOT_FOUND("RECORD_NOT_FOUND", 3),
    MEDIA_NOT_FOUND("MEDIA_NOT_FOUND", 4),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", 5),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", 6),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", 7),
    PLACE_NOT_FOUND("PLACE_NOT_FOUND",8),
    WAITER_NOT_FOUND("WAITER_NOT_FOUND",9),
    USER_NOT_FOUND("USER_NOT_FOUND",10),
    FILE_NOT_FOUND("FILE_NOT_FOUND", 11),
    PATH_IS_NULL_OR_WRONG("PATH_IS_NULL_OR_WRONG", 12);

    private String message;
    private int errorCode;

    ErrorMessage(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public int value() {
        return this.errorCode;
    }

    @Override
    public String toString() {
        return message;

    }

}
