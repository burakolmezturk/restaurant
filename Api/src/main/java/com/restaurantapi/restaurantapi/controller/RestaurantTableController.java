package com.restaurantapi.restaurantapi.controller;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.entity.RestaurantTable;
import com.restaurantapi.restaurantapi.services.PlaceService;
import com.restaurantapi.restaurantapi.services.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/table")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/list")
    public List<RestaurantTable> getAllTables() {
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/{id}")
    public RestaurantTable getTableById(@PathVariable int id) {
        return restaurantTableService.getTableById(id);
    }

    @PostMapping("/add")
    public RestaurantTable addTable(@RequestBody RestaurantTable restaurantTable,@RequestParam int placeId) {
        return restaurantTableService.addTable(restaurantTable,placeId);
    }

 /*   @PutMapping("/update")
    public RestaurantTable putTable(@RequestBody RestaurantTable restaurantTable,@RequestParam int placeId) {

        Optional<Place> place = placeService.getPlaceById(placeId);
        if (!place.isPresent()){
            return null;
        }
        place.get().getRestaurantTableSet().add(restaurantTableService.editTable(restaurantTable));
        return restaurantTableService.editTable(restaurantTable);
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteTable(@PathVariable int id) {
        restaurantTableService.deleteTable(id);
    }


    @GetMapping("/tables")
    public Set<RestaurantTable> getProductsByCategoryId(@RequestParam int placeId)
    {
      return   restaurantTableService.getTableByPlaceId(placeId);
    }


}
