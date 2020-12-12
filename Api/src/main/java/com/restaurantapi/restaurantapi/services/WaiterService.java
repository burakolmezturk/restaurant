package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.WaiterDTOConvertor;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import com.restaurantapi.restaurantapi.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private MediaRepository mediaRepository;

    public WaiterDTO addWaiter(WaiterDTO waiterDTO) {
        Media media = mediaRepository.findById(waiterDTO.getImage().getId()).get();
        Waiter waiter = WaiterDTOConvertor.dtoToWaiter(waiterDTO);
        waiter.setImage(media);
        return WaiterDTOConvertor.waiterToDTO(waiterRepository.save(waiter));
    }

    public WaiterDTO editWaiter(WaiterDTO waiterDTO) {
        return WaiterDTOConvertor.waiterToDTO(waiterRepository.saveAndFlush(WaiterDTOConvertor.dtoToWaiter(waiterDTO)));
    }

    public List<WaiterDTO> getAllWaiters() {
        List<Waiter> waiters = waiterRepository.findAll();
        List<WaiterDTO> waiterDTOList = new ArrayList<>();
        waiters.stream().forEach(waiter -> waiterDTOList.add(WaiterDTOConvertor.waiterToDTO(waiter)));
        return waiterDTOList;
    }

    public boolean deleteWaiterById(int id) {
        if (waiterRepository.existsById(id)) {
            waiterRepository.deleteById(id);
            return true;
        } else return false;
    }

    public WaiterDTO getWaiterById(int id) {
        Optional<Waiter> waiterOptional = waiterRepository.findById(id);
        if (!waiterOptional.isPresent()) {
            return null;
        }
        return WaiterDTOConvertor.waiterToDTO(waiterOptional.get());
    }
}
