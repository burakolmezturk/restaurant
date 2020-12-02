package com.restaurantapi.restaurantapi.controller;


import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("/list")
    public List<Place> getPlaces(){ return placeService.getPlaces(); }
    @PostMapping("/add")
    public void addPlace(@RequestBody Place place){
        placeService.addPlace(place);
    }
    @PutMapping("/edit")
    public Place updatePlace(@RequestBody Place place){
        return placeService.editPlace(place);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePlace(@PathVariable int id){
        placeService.deletePlace(id);
    }
    @GetMapping("/{placeId}")
    public Place getPlaceById(@PathVariable int placeId){
        return placeService.getPlaceById(placeId).get();
    }
}
