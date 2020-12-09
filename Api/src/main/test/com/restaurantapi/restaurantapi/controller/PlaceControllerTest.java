package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.PlaceDTOBuilder;
import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.services.PlaceService;
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

@RunWith(MockitoJUnitRunner.class)
public class PlaceControllerTest {
    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;
    private PlaceDTO placeDTO = new PlaceDTOBuilder().id(1).name("bahce").tableCount(5).build();

    private List<PlaceDTO> placeDTOList = new ArrayList<>();

    @Before
    public void setUp() {
        placeDTOList.add(placeDTO);
    }
    @Test
    public void ShouldGetPlaces(){
        Mockito.when(placeService.getPlaces()).thenReturn(placeDTOList);
        List<PlaceDTO> res = placeController.getPlaces();
        Assert.assertEquals(res,placeDTOList);
    }
    @Test
    public void ShouldNotGetPlaces(){
        Mockito.when(placeService.getPlaces()).thenReturn(new ArrayList<>());
        List<PlaceDTO> res = placeController.getPlaces();
        Assert.assertNotEquals(res,placeDTOList);
    }
    @Test
    public void ShouldAddPlace(){
        Mockito.when(placeService.addPlace(Mockito.any())).thenReturn(true);
        Boolean res = placeController.addPlace(placeDTO);
        Assert.assertEquals(true,res);
    }
    @Test
    public void ShouldNotAddPlace(){
        Mockito.when(placeService.addPlace(Mockito.any())).thenReturn(false);
        Boolean res = placeController.addPlace(placeDTO);
        Assert.assertEquals(false,res);
    }
    @Test
    public void ShouldUpdatePlace(){
        Mockito.when(placeService.editPlace(Mockito.any())).thenReturn(placeDTO);
        PlaceDTO res = placeController.updatePlace(placeDTO);
        Assert.assertEquals(res,placeDTO);
    }
    @Test
    public void ShouldNotUpdatePlace(){
        Mockito.when(placeService.editPlace(Mockito.any())).thenReturn(new PlaceDTO());
        PlaceDTO res = placeController.updatePlace(placeDTO);
        Assert.assertNotEquals(res,placeDTO);
    }
    @Test
    public void ShouldDeletePlace(){
        Mockito.when(placeService.deletePlace(1)).thenReturn(true);
        Boolean res = placeController.deletePlace(1);
        Assert.assertEquals(true,res);
    }
    @Test
    public void ShouldNotDeletePlace(){
        Mockito.when(placeService.deletePlace(1)).thenReturn(false);
        Boolean res = placeController.deletePlace(1);
        Assert.assertEquals(false,res);
    }
    @Test
    public void ShouldGetPlaceById(){
        Mockito.when(placeService.getPlaceById(1)).thenReturn(placeDTO);
        PlaceDTO res = placeController.getPlaceById(1);
        Assert.assertEquals(res,placeDTO);
    }
    @Test
    public void ShouldNotGetPlaceById(){
        Mockito.when(placeService.getPlaceById(1)).thenReturn(new PlaceDTO());
        PlaceDTO res = placeController.getPlaceById(1);
        Assert.assertNotEquals(res,placeDTO);
    }
}
