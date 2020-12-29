package com.restaurantapi.restaurantapi.mapper;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);

    List<Customer> toEntityList(List<CustomerDTO> customerDTOList);
    List<CustomerDTO> toDTOList(List<Customer> customerList);

}
