package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.builder.CustomerDTOBuilder;
import com.restaurantapi.restaurantapi.builder.MediaDTOBuilder;
import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Category;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.entity.Media;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.mapper.CategoryMapper;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.mapper.MediaMapper;
import com.restaurantapi.restaurantapi.repository.CustomerRepository;
import org.apache.xmlbeans.impl.soap.SOAPException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);
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

    private Customer customer = customerMapper.toEntity(customerDTO);
    private Media media = mediaMapper.toEntity(mediaDTO);
    List<Customer> customers = new ArrayList<>();
    List<CustomerDTO> customerDTOList = new ArrayList<>();
    private Pageable pageable = PageRequest.of(0, 10);

    @Before
    public void setUp() {
        customerDTOList.add(customerDTO);
        customers = customerMapper.toEntityList(customerDTOList);
    }

    @Test
    public void shouldAddCustomer() throws IOException, JAXBException, SOAPException {
        customerService.addCustomer(customerDTO);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    public void shouldEditCustomer() throws JAXBException, IOException {
        customerService.editCustomer(customerDTO);
        Mockito.verify(customerRepository, Mockito.times(1)).saveAndFlush(Mockito.any());

    }

    @Test
    public void shouldDeleteCustomer() throws JAXBException, IOException {
        int id = 1;
        customerService.deleteCustomer(id);
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void shouldGetCustomerById() {
        int id = 1;
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
        CustomerDTO res = customerService.getCustomerById(id);
        Assert.assertEquals(res.getId(), customerDTO.getId());
    }


    @Test
    public void shouldGetCustomersPageByName() {

        Mockito.when(customerRepository.findCustomersByNameContains("deneme", pageable)).thenReturn(new PageImpl<Customer>(customers, pageable, 1));
        Page<CustomerDTO> customerPage = customerService.getCustomersPageByName("deneme", pageable);

        Assert.assertEquals(customerPage.getContent().get(0).getId(),customerDTO.getId());

    }

    @Test
    public void shouldGetCustomersByPage() throws JAXBException, IOException, SOAPException {
        Mockito.when(customerRepository.findAll(pageable)).thenReturn(new PageImpl<Customer>(customers, pageable, 1));
        Page<CustomerDTO> customerPage = customerService.getCustomersByPage(pageable);

        Assert.assertEquals(customerPage.getContent().get(0).getId(),customerDTO.getId());
    }

}
