package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.convertor.WaiterDTOConvertor;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.entity.Waiter;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.WaiterMapper;
import com.restaurantapi.restaurantapi.repository.MediaRepository;
import com.restaurantapi.restaurantapi.repository.WaiterRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

  @Autowired private WaiterRepository waiterRepository;

  @Autowired private MediaRepository mediaRepository;

  @Autowired private WaiterMapper waiterMapper;

  @Transactional(propagation = Propagation.REQUIRED)
  public WaiterDTO addWaiter(WaiterDTO waiterDTO) {

    Optional<Media> optionalMedia = mediaRepository.findById(waiterDTO.getImage().getId());
    if (!optionalMedia.isPresent()) throw new RecordNotFoundException(ErrorMessage.MEDIA_NOT_FOUND);

    Waiter waiter = waiterMapper.toEntity(waiterDTO);

    waiter.setImage(optionalMedia.get());
    return waiterMapper.toDTO(waiterRepository.save(waiter));
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public WaiterDTO editWaiter(WaiterDTO waiterDTO) {

    return waiterMapper.toDTO(waiterRepository.saveAndFlush(waiterMapper.toEntity(waiterDTO)));
  }

  public List<WaiterDTO> getAllWaiters() {
    List<Waiter> waiters = waiterRepository.findAll();
    if (waiters.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    List<WaiterDTO> waiterDTOList = waiterMapper.toEntityList(waiters);
    return waiterDTOList;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public boolean deleteWaiterById(int id) {
    if (!waiterRepository.existsById(id))
      throw new RecordNotFoundException(ErrorMessage.ID_IS_NULL);

    waiterRepository.deleteById(id);
    return true;
  }

  public WaiterDTO getWaiterById(int id) {
    Optional<Waiter> waiterOptional = waiterRepository.findById(id);
    if (!waiterOptional.isPresent())
      throw new RecordNotFoundException(ErrorMessage.WAITER_NOT_FOUND);

    return waiterMapper.toDTO(waiterOptional.get());
  }
}
