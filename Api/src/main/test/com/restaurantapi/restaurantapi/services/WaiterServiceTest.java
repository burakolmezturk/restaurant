package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.WaiterDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.WaiterDTOConvertor;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.repository.WaiterRepository;
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
public class WaiterServiceTest {

    @InjectMocks
    private WaiterService waiterService;

    @Mock
    private WaiterRepository waiterRepository;

    private WaiterDTO waiterDTO = new WaiterDTOBuilder()
            .name("ahmet")
            .age(22)
            .id(1)
            .email("ahmet@htm")
            .phone("4567890")
            .build();
    private List<WaiterDTO> waiterDTOList = new ArrayList<>();
    private Waiter waiter = WaiterDTOConvertor.dtoToWaiter(waiterDTO);
    private List<Waiter> waiterList = new ArrayList<>();

    @Before
    public void setUp() {
        waiterDTOList.add(waiterDTO);
        waiterDTOList.stream().forEach(waiter -> waiterList.add(WaiterDTOConvertor.dtoToWaiter(waiter)));
    }

    @Test
    public void shouldAddWaiter() {
        Mockito.when(waiterRepository.save(Mockito.any())).thenReturn(waiter);
        WaiterDTO res = waiterService.addWaiter(waiterDTO);
        Assert.assertEquals(res.getId(), waiterDTO.getId());
    }

    @Test
    public void shouldNotAddWaiter() {
        Mockito.when(waiterRepository.save(Mockito.any())).thenReturn(new Waiter());
        WaiterDTO res = waiterService.addWaiter(waiterDTO);
        Assert.assertNotEquals(res.getId(), waiterDTO.getId());
    }

    @Test
    public void shouldEditWaiter() {
        Mockito.when(waiterRepository.saveAndFlush(Mockito.any())).thenReturn(waiter);
        WaiterDTO res = waiterService.editWaiter(waiterDTO);
        Assert.assertEquals(waiterDTO.getId(), res.getId());
    }

    @Test
    public void shouldNotEditWaiter() {
        Mockito.when(waiterRepository.saveAndFlush(Mockito.any())).thenReturn(new Waiter());
        WaiterDTO res = waiterService.editWaiter(waiterDTO);
        Assert.assertNotEquals(waiterDTO.getId(), res.getId());
    }

    @Test
    public void shouldGetAllWaiters() {
        Mockito.when(waiterRepository.findAll()).thenReturn(waiterList);
        List<WaiterDTO> res = waiterService.getAllWaiters();
        Assert.assertEquals(res.get(0).getId(),waiterList.get(0).getId());
    }
    @Test
    public void shouldDeleteWaiterById(){
        Mockito.when(waiterRepository.existsById(1)).thenReturn(true);
        Boolean res = waiterService.deleteWaiterById(1);
        Assert.assertEquals(true,res);
    }
    @Test
    public void shouldNotDeleteWaiterById(){
        Mockito.when(waiterRepository.existsById(1)).thenReturn(false);
        Boolean res = waiterService.deleteWaiterById(1);
        Assert.assertEquals(false,res);
    }
    @Test
    public void shouldGetWaiterById(){
        Mockito.when(waiterRepository.findById(1)).thenReturn(Optional.of(waiter));
        WaiterDTO res = waiterService.getWaiterById(1);
        Assert.assertEquals(waiterDTO.getId(),res.getId());
    }
}
