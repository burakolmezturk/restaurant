package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.PlaceDTOBuilder;
import com.restaurantapi.restaurantapi.dto.PlaceDTO;
import com.restaurantapi.restaurantapi.convertor.PlaceDTOConvertor;
import com.restaurantapi.restaurantapi.entity.Place;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.PlaceMapper;
import com.restaurantapi.restaurantapi.mapper.WaiterMapper;
import com.restaurantapi.restaurantapi.repository.PlaceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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

    @Spy
    private PlaceMapper placeMapper = Mappers.getMapper(PlaceMapper.class);

    private PlaceDTO placeDTO = new PlaceDTOBuilder().id(1).name("bahce").tableCount(5).build();

    private List<Place> places = new ArrayList<>();

    private List<PlaceDTO> placesDTO = new ArrayList<>();

    private Place place = new Place();

    @Before
    public void setUp() {
        place = placeMapper.toEntity(placeDTO);
        placesDTO.add(placeDTO);
        places = placeMapper.toEntityPlace(placesDTO);
    }

    @Test
    public void ShouldAddPlace() {
        Mockito.when(placeRepository.save(Mockito.any())).thenReturn(place);
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
        Mockito.when(placeRepository.findAll()).thenReturn(places);
        List<PlaceDTO> res = placeService.getPlaces();
        Assert.assertNotNull(res);
    }

    @Test
    public void ShouldEditPlace() {
        Mockito.when(placeRepository.saveAndFlush(Mockito.any())).thenReturn(place);
        PlaceDTO res = placeService.editPlace(placeDTO);
        Assert.assertNotNull(res);
        Assert.assertEquals(res, placeDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void ShouldNotEditPlace() {
        Mockito.when(placeRepository.saveAndFlush(null)).thenReturn(null);
        placeService.editPlace(null);

    }

    @Test
    public void ShouldDeletePlace() {
        int id = 1;
        Mockito.when(placeRepository.existsById(Mockito.any())).thenReturn(true);
        Boolean res = placeService.deletePlace(id);
        Assert.assertEquals(true, res);
    }

    @Test(expected = BusinessRuleException.class)
    public void ShouldNotDeletePlace() {
        int id = 1;
        Mockito.when(placeRepository.existsById(Mockito.any())).thenReturn(false);
        Boolean res = placeService.deletePlace(0);
    }

    @Test
    public void shouldGetPlaceById() {
        Mockito.when(placeRepository.findById(Mockito.any())).thenReturn(Optional.of(place));
        PlaceDTO res = placeService.getPlaceById(1);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(), placeDTO.getId());
    }


}
