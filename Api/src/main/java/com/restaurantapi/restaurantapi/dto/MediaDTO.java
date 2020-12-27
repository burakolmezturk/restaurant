package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO extends BaseEntity {

    private byte[] fileContent;
    private String fileName;
}
