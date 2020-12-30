package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.builder.CustomerDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.services.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

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

    private Pageable pageable = PageRequest.of(0, 10);
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    @Before
    public void setUp(){
         customerDTOList.add(customerDTO);
    }
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
        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(id);
    }

    @Test
    public void shouldGetCustomerById(){

        int id = 1;
        Mockito.when(customerService.getCustomerById(id)).thenReturn(customerDTO);
        CustomerDTO res =customerController.getCustomerById(id);
        Assert.assertEquals(res.getId(), customerDTO.getId());
    }
    @Test
    public void shouldGetCustomerByName(){

        Mockito.when(customerService.getCustomersPageByName("deneme",pageable)).thenReturn(new PageImpl<CustomerDTO>(customerDTOList, pageable, 1));
        Page<CustomerDTO> res = customerController.getCustomerByName("deneme",pageable);
        Assert.assertEquals(res.getContent().get(0).getId(), customerDTOList.get(0).getId());

    }


}
