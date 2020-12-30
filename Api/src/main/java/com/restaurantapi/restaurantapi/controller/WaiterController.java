package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiter")
@Validated
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @PostMapping("/add")
    public WaiterDTO addWaiter(@Valid @RequestBody @NotNull(message = "{WAITER_NOT_FOUND}") WaiterDTO waiterDTO) {
        return waiterService.addWaiter(waiterDTO);
    }

    @PutMapping("/edit")
    public WaiterDTO editWaiter(@Valid @RequestBody @NotNull(message = "{WAITER_NOT_FOUND}") WaiterDTO waiterDTO) {
        return waiterService.editWaiter(waiterDTO);
    }

    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter() {
        return waiterService.getAllWaiters();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable  @Min(value = 1, message = "{ID_CONTROL}") int id) {
        if (waiterService.deleteWaiterById(id)) return true;
        else return false;
    }

    @GetMapping("/{id}")
    public WaiterDTO getWaiterById(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int id) {
        return waiterService.getWaiterById(id);
    }

}
