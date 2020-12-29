package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.CustomerDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.services.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private MediaDTO mediaDTO = new MediaDTOBuilder()
            .fileContent(null)
            .fileName("deneme")
            .id(1).build();

    private CustomerDTO customerDTO = new CustomerDTOBuilder()
            .address("deneme")
            .id(1)
            .name("ahmet")
            .phone("000000")
            .surname("mehemet")
            .image(mediaDTO)
            .build();

    @Test
    public void shouldAddCustomer() {
        customerController.addCustomer(customerDTO);
        Mockito.verify(customerService, Mockito.times(1)).addCustomer(Mockito.any());
    }

    @Test
    public void shouldEditCustomer() {
        customerController.editCustomer(customerDTO);
        Mockito.verify(customerService, Mockito.times(1)).editCustomer(Mockito.any());
    }

    @Test
    public void shouldDeleteCustomer() {
        int id = 1;
        customerController.deleteCustomer(id);
        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(Mockito.any());
    }

    @Test
    public void shouldGetCustomerById(){

        int id = 1;
        Mockito.when(customerService.getCustomerById(id)).thenReturn(customerDTO);
        CustomerDTO res =customerController.getCustomerById(id);
        Assert.assertEquals(res.getId(), customerDTO.getId());
    }


}
