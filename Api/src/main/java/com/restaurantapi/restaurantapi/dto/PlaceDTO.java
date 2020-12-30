package com.restaurantapi.restaurantapi.dto;

import com.restaurantapi.restaurantapi.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceDTO extends BaseDTO {
    @NotNull(message = "{NAME_IS_NULL}")
    @NotEmpty(message = "{NAME_IS_EMPTY}")
    private String name;
    @Min(value = 0 ,message = "{MIN_TABLE_COUNT}")
    private int tableCount;
    private MediaDTO image;

}
