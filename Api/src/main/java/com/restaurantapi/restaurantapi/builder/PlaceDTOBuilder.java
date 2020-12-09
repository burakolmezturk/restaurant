package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.entity.Place;

public class PlaceDTOBuilder extends Builder{
    private String name;
    private int tableCount;
    @Override
    public PlaceDTO build() {
        PlaceDTO placeDTO=new PlaceDTO();
        placeDTO.setTableCount(this.tableCount);
        placeDTO.setName(this.name);
        placeDTO.setId(super.id);
        return placeDTO;
    }
    public PlaceDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public PlaceDTOBuilder tableCount(int tableCount){
        this.tableCount=tableCount;
        return this;
    }
    public PlaceDTOBuilder id(int id){
        super.id=id;
        return this;
    }
}
