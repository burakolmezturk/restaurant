package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiter")
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @PostMapping("/add")
    public WaiterDTO addWaiter(@RequestBody WaiterDTO waiterDTO) {
        return waiterService.addWaiter(waiterDTO);
    }
    @PutMapping("/edit")
    public WaiterDTO editWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.editWaiter(waiterDTO);
    }
    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter(){
        return waiterService.getAllWaiters();
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable int id){
        if (waiterService.deleteWaiterById(id)) return true;
        else return false;
    }
    @GetMapping("/{id}")
    public WaiterDTO getWaiterById(@PathVariable int id){
        return waiterService.getWaiterById(id);
    }

}
