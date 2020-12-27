package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceDTO toDTO(Place place);
    Place toEntity(PlaceDTO placeDTO);

    List<Place> toEntityPlace (List<PlaceDTO> placeDTO);
    List<PlaceDTO> toDTOPlace(List<Place> places);
}
