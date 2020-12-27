package com.restaurantapi.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum ErrorMessage {
    ID_IS_NULL("Id couldn't be null", 1),
    ENTITY_IS_NULL("Entity couldn't be null", 2),
    RECORD_NOT_FOUND("No Records Found", 3),
    MEDIA_NOT_FOUND("No Media Found", 4),
    CATEGORY_NOT_FOUND("No Category Found", 5),
    PRODUCT_NOT_FOUND("No Category Found", 6),
    ROLE_NOT_FOUND("No Role Found", 7),
    PLACE_NOT_FOUND("No Place Found",8),
    WAITER_NOT_FOUND("No Waiter Found",9),
    USER_NOT_FOUND("No Waiter Found",10),
    FILE_NOT_FOUND("No File Found", 11),
    PATH_IS_NULL_OR_WRONG("Path Is Null Or Wrong", 12);


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
