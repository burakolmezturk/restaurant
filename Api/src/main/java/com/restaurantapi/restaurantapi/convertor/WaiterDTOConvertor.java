package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.WaiterDTO;
import com.restaurantapi.restaurantapi.entity.Waiter;

public class WaiterDTOConvertor {
    private WaiterDTOConvertor(){
    }
    public static WaiterDTO waiterToDTO(Waiter waiter){
        WaiterDTO waiterDTO = new WaiterDTO();
        waiterDTO.setId(waiter.getId());
        waiterDTO.setName(waiter.getName());
        waiterDTO.setAge(waiter.getAge());
        waiterDTO.setEmail(waiter.getEmail());
        waiterDTO.setPhone(waiter.getPhone());
        return waiterDTO;
    }
    public static Waiter dtoToWaiter (WaiterDTO waiterDTO){
        Waiter waiter = new Waiter();
        waiter.setId(waiterDTO.getId());
        waiter.setName(waiterDTO.getName());
        waiter.setAge(waiterDTO.getAge());
        waiter.setEmail(waiterDTO.getEmail());
        waiter.setPhone(waiterDTO.getPhone());
        return waiter;
    }
}
