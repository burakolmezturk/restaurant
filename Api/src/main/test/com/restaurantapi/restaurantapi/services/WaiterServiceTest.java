package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.builder.WaiterDTOBuilder;
import com.restaurantapi.restaurantapi.convertor.MediaDTOConvertor;
import com.restaurantapi.restaurantapi.convertor.WaiterDTOConvertor;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.mapper.WaiterMapper;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import com.restaurantapi.restaurantapi.repository.WaiterRepository;
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
public class WaiterServiceTest {

    @InjectMocks
    private WaiterService waiterService;

    @Mock
    private WaiterRepository waiterRepository;

    @Mock
    private MediaRepository mediaRepository;

    @Spy
    private WaiterMapper waiterMapper = Mappers.getMapper(WaiterMapper.class);

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

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
    private Waiter waiter = waiterMapper.toEntity(waiterDTO);
    private List<Waiter> waiterList = new ArrayList<>();
    private Media media = mediaMapper.toEntity(mediaDTO);

    @Before
    public void setUp() {
        waiterDTOList.add(waiterDTO);
        waiterList = waiterMapper.toDTOList(waiterDTOList);
    }

    @Test
    public void shouldAddWaiter() {
        Mockito.when(mediaRepository.findById(1)).thenReturn(Optional.of(media));
        Mockito.when(waiterRepository.save(Mockito.any())).thenReturn(waiter);
        WaiterDTO res = waiterService.addWaiter(waiterDTO);
        Assert.assertEquals(res.getId(), waiterDTO.getId());
    }

    @Test
    public void shouldEditWaiter() {
        Mockito.when(waiterRepository.saveAndFlush(Mockito.any())).thenReturn(waiter);
        WaiterDTO res = waiterService.editWaiter(waiterDTO);
        Assert.assertEquals(waiterDTO.getId(), res.getId());
    }

    @Test
    public void shouldGetAllWaiters() {
        Mockito.when(waiterRepository.findAll()).thenReturn(waiterList);
        List<WaiterDTO> res = waiterService.getAllWaiters();
        Assert.assertEquals(res.get(0).getId(), waiterList.get(0).getId());
    }

    @Test
    public void shouldDeleteWaiterById() {
        Mockito.when(waiterRepository.existsById(1)).thenReturn(true);
        Boolean res = waiterService.deleteWaiterById(1);
        Assert.assertEquals(true, res);
    }

    @Test(expected = RecordNotFoundException.class)
    public void shouldNotDeleteWaiterById() {
        Mockito.when(waiterRepository.existsById(1)).thenReturn(false);
        Boolean res = waiterService.deleteWaiterById(1);
        Assert.assertEquals(false, res);
    }

    @Test
    public void shouldGetWaiterById() {
        Mockito.when(waiterRepository.findById(1)).thenReturn(Optional.of(waiter));
        WaiterDTO res = waiterService.getWaiterById(1);
        Assert.assertEquals(waiterDTO.getId(), res.getId());
    }
}
