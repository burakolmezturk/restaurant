package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.convertor.PlaceDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public List<PlaceDTO> getPlaces() {

        List<PlaceDTO> placeDTOList = new ArrayList<>();

        placeRepository.findAll().stream().forEach(place -> placeDTOList.add(PlaceDTOConvertor.placeToDTO(place)));
        return placeDTOList;
    }

    public boolean addPlace(PlaceDTO placeDTO) {

        Place place = placeRepository.save(PlaceDTOConvertor.dtoToPlace(placeDTO));
        if (place.getId() != 0) return true;
        else return false;


    }

    public PlaceDTO editPlace(PlaceDTO placeDTO) {
        placeRepository.saveAndFlush(PlaceDTOConvertor.dtoToPlace(placeDTO));
        return placeDTO;
    }

    public boolean deletePlace(int id) {
        if (placeRepository.existsById(id)) {
            placeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }


    }

    public PlaceDTO getPlaceById(int placeId) {
        Optional<Place> place = placeRepository.findById(placeId);


        if (!place.isPresent()) {
            return null;
        }
        return PlaceDTOConvertor.placeToDTO(place.get());

    }
}
