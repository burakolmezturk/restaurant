package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public Page<CustomerDTO> getCustomersByPage(Pageable pageable){
        return customerService.getCustomersByPage(pageable);
    }

    @PostMapping()
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
    }

    @PutMapping()
    public void editCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.editCustomer(customerDTO);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable int customerId){
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable int customerId){
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/search")
    public Page<CustomerDTO> getCustomerById(@RequestParam String customerName,Pageable pageable){
        return customerService.getCustomersPageByName(customerName,pageable);
    }
}
