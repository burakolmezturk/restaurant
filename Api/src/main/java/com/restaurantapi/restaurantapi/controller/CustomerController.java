package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ProductDTO;
import com.restaurantapi.restaurantapi.services.CustomerService;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@RestController
@Validated
public class CustomerController {

  @Autowired private CustomerService customerService;

  @GetMapping()
  public Page<CustomerDTO> getCustomersByPage(@Valid @NotNull Pageable pageable) throws JAXBException, IOException, SOAPException {
    return customerService.getCustomersByPage(pageable);
  }

  @PostMapping()
  public void addCustomer(
      @Valid @RequestBody @NotNull(message = "{CUSTOMER_NOT_FOUND}") CustomerDTO customerDTO) throws IOException, JAXBException, SOAPException {
    customerService.addCustomer(customerDTO);
  }

  @PutMapping()
  public void editCustomer(
      @Valid @RequestBody @NotNull(message = "{CUSTOMER_NOT_FOUND}") CustomerDTO customerDTO) throws JAXBException, IOException {
    customerService.editCustomer(customerDTO);
  }

  @DeleteMapping("/{customerId}")
  public void deleteCustomer(
      @PathVariable @Min(value = 1, message = "{ID_CONTROL}") int customerId) throws JAXBException, IOException {
    customerService.deleteCustomer(customerId);
  }

  @GetMapping("/{customerId}")
  public CustomerDTO getCustomerById(
      @PathVariable @Min(value = 1, message = "{ID_CONTROL}") int customerId) {
    return customerService.getCustomerById(customerId);
  }

  @GetMapping("/search")
  public Page<CustomerDTO> getCustomerByName(
      @RequestParam String customerName, @NotNull Pageable pageable) {
    return customerService.getCustomersPageByName(customerName, pageable);
  }
}
