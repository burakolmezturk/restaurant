package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.CategoryDTO;
import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Product;

import java.util.HashSet;
import java.util.Set;


public class CustomerDTOBuilder extends Builder{


    private int id;
    private String name;
    private MediaDTO image;
    private String surname;
    private String address;
    private String phone;

    @Override
    public CustomerDTO build() {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(super.id);
        customerDTO.setImage(this.image);
        customerDTO.setAddress(this.address);
        customerDTO.setName(this.name);
        customerDTO.setSurname(this.surname);
        customerDTO.setPhone(this.phone);

        return customerDTO;
    }
    public CustomerDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public CustomerDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public CustomerDTOBuilder surname(String surname){
        this.surname=surname;
        return this;
    }
    public CustomerDTOBuilder image(MediaDTO image){
        this.image=image;
        return this;
    }
    public CustomerDTOBuilder phone(String phone){
        this.phone=phone;
        return this;
    }
    public CustomerDTOBuilder address(String address){
        this.address=address;
        return this;
    }
}
