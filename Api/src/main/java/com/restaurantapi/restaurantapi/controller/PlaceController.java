package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/place")
@Validated
public class PlaceController {

  @Autowired private PlaceService placeService;

  @GetMapping("/list")
  public List<PlaceDTO> getPlaces() {
    return placeService.getPlaces();
  }

  @PostMapping("/add")
  public boolean addPlace(
      @Valid @RequestBody @NotNull(message = "{PLACE_NOT_FOUND}") PlaceDTO placeDTO) {
    if (placeService.addPlace(placeDTO)) return true;
    else return false;
  }

  @PutMapping("/edit")
  public PlaceDTO updatePlace(
      @Valid @RequestBody @NotNull(message = "{PLACE_NOT_FOUND}") PlaceDTO placeDTO) {
    return placeService.editPlace(placeDTO);
  }

  @DeleteMapping("/delete/{id}")
  public boolean deletePlace(@PathVariable @Min(value = 1, message = "{ID_CONTROL}") int id) {
    if (placeService.deletePlace(id)) return true;
    else return false;
  }

  @GetMapping("/{placeId}")
  public PlaceDTO getPlaceById(
      @PathVariable @Min(value = 1, message = "{ID_CONTROL}") int placeId) {
    return placeService.getPlaceById(placeId);
  }
}
