package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.entity.Place;

public class PlaceDTOConvertor {

    private PlaceDTOConvertor() {
    }

    public static Place dtoToPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        place.setTableCount(placeDTO.getTableCount());

        return place;
    }

    public static PlaceDTO placeToDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        placeDTO.setTableCount(place.getTableCount());

        return placeDTO;
    }


}
