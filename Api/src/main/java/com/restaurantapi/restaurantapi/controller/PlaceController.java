package com.restaurantapi.restaurantapi.controller;


import com.restaurantapi.restaurantapi.dto.PlaceDTO;
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
    public List<PlaceDTO> getPlaces(){ return placeService.getPlaces(); }
    @PostMapping("/add")
    public boolean addPlace(@RequestBody PlaceDTO placeDTO){
        if (placeService.addPlace(placeDTO)) return true;
        else return false;

    }
    @PutMapping("/edit")
    public PlaceDTO updatePlace(@RequestBody PlaceDTO placeDTO){
        return placeService.editPlace(placeDTO);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deletePlace(@PathVariable int id){
        if(placeService.deletePlace(id)) return true;
        else return false;
    }
    @GetMapping("/{placeId}")
    public PlaceDTO getPlaceById(@PathVariable int placeId){
        return placeService.getPlaceById(placeId);
    }
}
