package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.builder.WaiterDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.WaiterDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.services.WaiterService;
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
public class WaiterControllerTest {
    @InjectMocks
    private WaiterController waiterController;

    @Mock
    private WaiterService waiterService;
    private MediaDTO mediaDTO = new MediaDTOBuilder()
            .fileContent(null)
            .fileName("deneme")
            .id(1).build();
    private WaiterDTO waiterDTO = new WaiterDTOBuilder()
            .name("ahmet")
            .age(22)
            .id(1)
            .email("ahmet@htm")
            .phone("4567890")
            .image(mediaDTO)
            .build();
    private List<WaiterDTO> waiterDTOList = new ArrayList<>();
    private Waiter waiter = WaiterDTOConvertor.dtoToWaiter(waiterDTO);
    private List<Waiter> waiterList = new ArrayList<>();

    @Before
    public void setUp(){
        waiterDTOList.add(waiterDTO);
        waiterDTOList.stream().forEach(waiter -> waiterList.add(WaiterDTOConvertor.dtoToWaiter(waiter)));
    }
    @Test
    public void shouldAddWaiter(){
        Mockito.when(waiterService.addWaiter(Mockito.any())).thenReturn(waiterDTO);
        WaiterDTO res = waiterController.addWaiter(waiterDTO);
        Assert.assertEquals(waiterDTO.getId(),res.getId());

    }
    @Test
    public void shouldNotAddWaiter(){
        Mockito.when(waiterService.addWaiter(Mockito.any())).thenReturn(new WaiterDTO());
        WaiterDTO res = waiterController.addWaiter(waiterDTO);
        Assert.assertNotEquals(waiterDTO.getId(),res.getId());
    }
    @Test
    public void shouldEditWaiter(){
        Mockito.when(waiterService.editWaiter(Mockito.any())).thenReturn(waiterDTO);
        WaiterDTO res = waiterController.editWaiter(waiterDTO);
        Assert.assertEquals(waiterDTO.getId(),res.getId());
    }
    @Test
    public void shouldNotEditWaiter(){
        Mockito.when(waiterService.editWaiter(Mockito.any())).thenReturn(new WaiterDTO());
        WaiterDTO res = waiterController.editWaiter(waiterDTO);
        Assert.assertNotEquals(waiterDTO.getId(),res.getId());
    }
    @Test
    public void shouldGetAllWaiter(){
        Mockito.when(waiterService.getAllWaiters()).thenReturn(waiterDTOList);
        List<WaiterDTO> res = waiterController.getAllWaiter();
        Assert.assertEquals(waiterDTOList.get(0).getId(),res.get(0).getId());
    }
    @Test
    public void shouldDeleteCategory(){
        Mockito.when(waiterService.deleteWaiterById(1)).thenReturn(true);
        Boolean res = waiterController.deleteCategory(1);
        Assert.assertEquals(true,res);
    }
    @Test
    public void shouldNotDeleteCategory(){
        Mockito.when(waiterService.deleteWaiterById(1)).thenReturn(false);
        Boolean res = waiterController.deleteCategory(1);
        Assert.assertEquals(false,res);
    }
    @Test
    public void shouldGetWaiterById(){
        Mockito.when(waiterService.getWaiterById(1)).thenReturn(waiterDTO);
        WaiterDTO res = waiterController.getWaiterById(1);
        Assert.assertEquals(waiterDTO.getId(),res.getId());
    }
}
