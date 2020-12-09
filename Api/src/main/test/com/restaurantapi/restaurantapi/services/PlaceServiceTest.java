package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.PlaceDTOBuilder;
import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.convertor.PlaceDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.repository.PlaceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceTest {
    @InjectMocks
    private PlaceService placeService;

    @Mock
    private PlaceRepository placeRepository;

    private PlaceDTO placeDTO = new PlaceDTOBuilder().id(1).name("bahce").tableCount(5).build();

    private List<Place> places = new ArrayList<>();


    @Test
    public void ShouldAddPlace() {
        Mockito.when(placeRepository.save(Mockito.any())).thenReturn(PlaceDTOConvertor.dtoToPlace(placeDTO));
        Boolean res = placeService.addPlace(placeDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(true, res);

    }

    @Test
    public void NotShouldAddPlace() {
        Mockito.when(placeRepository.save(Mockito.any())).thenReturn(new Place());
        Boolean res = placeService.addPlace(placeDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(false, res);
    }

    @Test
    public void ShouldGetPlaces() {
        places.add(PlaceDTOConvertor.dtoToPlace(placeDTO));
        Mockito.when(placeRepository.findAll()).thenReturn(places);
        List<PlaceDTO> res = placeService.getPlaces();
        Assert.assertNotNull(res);
    }

    @Test
    public void ShouldEditPlace() {
        Mockito.when(placeRepository.saveAndFlush(Mockito.any())).thenReturn(PlaceDTOConvertor.dtoToPlace(placeDTO));
        PlaceDTO res = placeService.editPlace(placeDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, placeDTO);
    }

    @Test
    public void ShouldDeletePlace() {
        int id = 1;
        Mockito.when(placeRepository.existsById(Mockito.any())).thenReturn(true);
        Boolean res = placeService.deletePlace(id);
        Assert.assertEquals(true, res);
    }

    @Test
    public void ShouldNotDeletePlace() {
        int id = 1;
        Mockito.when(placeRepository.existsById(Mockito.any())).thenReturn(false);
        Boolean res = placeService.deletePlace(id);
        Assert.assertEquals(false, res);
    }

    @Test
    public void ShouldGetCategoryById() {
        Mockito.when(placeRepository.findById(Mockito.any())).thenReturn(Optional.of(PlaceDTOConvertor.dtoToPlace(placeDTO)));
        PlaceDTO res = placeService.getPlaceById(1);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), placeDTO.getId());
    }
}
