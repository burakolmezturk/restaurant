package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.WaiterDTO;


public class WaiterDTOBuilder extends Builder {
    private String name;
    private String email;
    private int age;
    private String phone;


    @Override
    public WaiterDTO build() {
        WaiterDTO waiterDTO=new WaiterDTO();
        waiterDTO.setId(super.id);
        waiterDTO.setName(this.name);
        waiterDTO.setEmail(this.email);
        waiterDTO.setAge(this.age);
        waiterDTO.setPhone(this.phone);
        return waiterDTO;
    }
    public WaiterDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public WaiterDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public WaiterDTOBuilder email(String email){
        this.email=email;
        return this;
    }
    public WaiterDTOBuilder phone(String phone){
        this.phone=phone;
        return this;
    }
    public WaiterDTOBuilder age(int age){
        this.age=age;
        return this;
    }
}
