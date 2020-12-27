package com.restaurantapi.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private Date timestamp;
    private String message;
    private String details;
    private int errorCode;

}
