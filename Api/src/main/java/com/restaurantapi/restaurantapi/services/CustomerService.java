package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.exception.SystemException;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.repository.CustomerRepository;
import liquibase.pro.packaged.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {

        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<Customer> pageContentList = customerPage.getContent();

        if (pageContentList.isEmpty()) throw new SystemException(ErrorMessage.RECORD_NOT_FOUND);

        return new PageImpl<CustomerDTO>
                (customerMapper.toDTOList(pageContentList)
                        , pageable
                        , customerPage.getTotalElements());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(CustomerDTO customerDTO) {

        customerRepository.save(customerMapper.toEntity(customerDTO));

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editCustomer(CustomerDTO customerDTO) {

        customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(int customerId) {

        customerRepository.deleteById(customerId);
    }

    public CustomerDTO getCustomerById(int customerId) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return customerMapper.toDTO(customerOptional.get());
    }

    public Page<CustomerDTO> getCustomersPageByName(String customerName, Pageable pageable) {

        Page<Customer> customerPage = customerRepository.findCustomersByNameContains(customerName, pageable);
        List<Customer> pageContentList = customerPage.getContent();

        if (pageContentList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return new PageImpl<>
                (customerMapper.toDTOList(pageContentList)
                        , pageable
                        , customerPage.getTotalElements());

    }

}
