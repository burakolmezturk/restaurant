package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getPlaces(){
        return placeRepository.findAll();
    }
    public void addPlace(Place place){
        placeRepository.save(place);
    }
    public Place editPlace(Place place){
       return placeRepository.saveAndFlush(place);
    }
    public void deletePlace(int id){
        placeRepository.deleteById(id);
    }
    public Optional<Place> getPlaceById(int placeId){
        Optional<Place> place = placeRepository.findById(placeId);
        if(place.isPresent()){
            return place;
        }else {return null;}

    }
}
