package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.convertor.PlaceDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.PlaceMapper;
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

    @Autowired
    private PlaceMapper placeMapper;

    public List<PlaceDTO> getPlaces() {

        List<Place> places = placeRepository.findAll();
        if(places.isEmpty())throw new BusinessRuleException(ErrorMessage.RECORD_NOT_FOUND);

        return placeMapper.toDTOPlace(places);
    }

    public boolean addPlace(PlaceDTO placeDTO) {

        if(placeDTO==null) throw new BusinessRuleException(ErrorMessage.PLACE_NOT_FOUND);

        Place place = placeRepository.save(placeMapper.toEntity(placeDTO));

        if (place.getId() != 0) return true;
        else return false;

    }

    public PlaceDTO editPlace(PlaceDTO placeDTO) {
        if(placeDTO==null)throw new BusinessRuleException(ErrorMessage.PLACE_NOT_FOUND);

        placeRepository.saveAndFlush(placeMapper.toEntity(placeDTO));
        return placeDTO;
    }

    public boolean deletePlace(int id) {

        if(id<=0)throw new BusinessRuleException(ErrorMessage.ID_IS_NULL);
        if (!placeRepository.existsById(id)) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        placeRepository.deleteById(id);
        return true;

    }

    public PlaceDTO getPlaceById(int placeId) {

        Optional<Place> place = placeRepository.findById(placeId);
        if (!place.isPresent()) throw new RecordNotFoundException(ErrorMessage.PLACE_NOT_FOUND);

        return placeMapper.toDTO(place.get());

    }
}
