package com.restaurantapi.restaurantapi.services;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.exception.BusinessRuleException;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.repository.CustomerRepository;
import liquibase.pro.packaged.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        if (pageable == null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<Customer> pageContentList = customerPage.getContent();

        if (pageContentList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return new PageImpl<CustomerDTO>
                (CustomerMapper.INSTANCE.toDTOList(pageContentList)
                        , pageable
                        , customerPage.getTotalElements());

    }

    public void addCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        customerRepository.save(CustomerMapper.INSTANCE.toEntity(customerDTO));

    }

    public void editCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

       if( customerDTO.getId() == 0)throw new BusinessRuleException(ErrorMessage.ID_IS_NULL);

        customerRepository.saveAndFlush(CustomerMapper.INSTANCE.toEntity(customerDTO));
    }

    public void deleteCustomer(int customerId) {
        if (customerId <= 0) throw new BusinessRuleException(ErrorMessage.ID_IS_NULL);

        customerRepository.deleteById(customerId);
    }

    public CustomerDTO getCustomerById(int customerId) {
        if (customerId <= 0) throw new BusinessRuleException(ErrorMessage.ID_IS_NULL);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(!customerOptional.isPresent()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return CustomerMapper.INSTANCE.toDTO(customerOptional.get());
    }

    public Page<CustomerDTO> getCustomersPageByName(String customerName,Pageable pageable) {
        if (pageable == null) throw new BusinessRuleException(ErrorMessage.ENTITY_IS_NULL);

        Page<Customer> customerPage = customerRepository.findCustomersByNameContains(customerName,pageable);
        List<Customer> pageContentList = customerPage.getContent();

        if (pageContentList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

        return new PageImpl<>
                (CustomerMapper.INSTANCE.toDTOList(pageContentList)
                        , pageable
                        , customerPage.getTotalElements());

    }

}
