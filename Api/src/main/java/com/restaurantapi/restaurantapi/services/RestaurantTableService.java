package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.entity.*;
import com.restaurantapi.restaurantapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantTableService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;


    public List<RestaurantTable> getAllTables() {

        return restaurantTableRepository.findAll();
    }

    public RestaurantTable addTable(RestaurantTable restaurantTable, int placeId) {
        Set<RestaurantTable> tables = new HashSet<>();
        Optional<Place> place = placeRepository.findById(placeId);
        tables.add(restaurantTable);
        if (place.isPresent()) {
           // place.get().getRestaurantTableSet().add(restaurantTable);
            return restaurantTableRepository.save(restaurantTable);
        } else {
            return null;
        }


    }


    public RestaurantTable editTable(RestaurantTable restaurantTable) {

        return restaurantTableRepository.saveAndFlush(restaurantTable);
    }

    public void deleteTable(int id) {
        restaurantTableRepository.deleteById(id);
    }

    public RestaurantTable getTableById(int id) {
        Optional<RestaurantTable> restaurantTable = restaurantTableRepository.findById(id);
        if (restaurantTable.isPresent()) {
            return restaurantTable.get();
        } else {
            return null;
        }

    }

    public Set<RestaurantTable> getTableByPlaceId(int placeId) {
        Optional<Place> place = placeRepository.findById(placeId);
        if (place.isPresent()) {
            return Collections.emptySet();
            //return place.get().getRestaurantTableSet();
        } else {
            return Collections.emptySet();
        }

    }

}
