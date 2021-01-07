package com.restaurantapi.restaurantapi.services;


import com.restaurantapi.restaurantapi.convertor.XMLConverter;
import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ErrorMessage;

import com.restaurantapi.restaurantapi.dto.ResponseDTO;
import com.restaurantapi.restaurantapi.entity.Customer;
import com.restaurantapi.restaurantapi.exception.RecordNotFoundException;
import com.restaurantapi.restaurantapi.exception.SystemException;
import com.restaurantapi.restaurantapi.mapper.CustomerMapper;
import com.restaurantapi.restaurantapi.repository.CustomerRepository;
import okhttp3.*;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
  @Autowired private CustomerRepository customerRepository;

  @Autowired private CustomerMapper customerMapper;

  public Page<CustomerDTO> getCustomersByPage(Pageable pageable) throws JAXBException, IOException, SOAPException {

//    OkHttpClient client = new OkHttpClient().newBuilder().build();
//    MediaType mediaType = MediaType.parse("text/plain");
//
//    RequestBody body = RequestBody.create(mediaType, XMLConverter.pageToXML(pageable.getPageSize(),pageable.getPageNumber(),""));
//    Request request = new Request.Builder()
//            .url("http://localhost:8080/SOAPCustomer/services/CustomerServiceImpl?wsdl")
//            .method("POST", body)
//            .addHeader("Content-Type", "text/plain")
//            .build();
//
//    Response response = client.newCall(request).execute();
//    String responseBody = response.body().string();
//    System.out.println("response : " + responseBody);
//
//    ResponseDTO responseDTO = XMLConverter.xmlToDTO(responseBody);

    Page<Customer> customerPage = customerRepository.findAll(pageable);
   List<Customer> pageContentList = customerPage.getContent();
   if (pageContentList.isEmpty()) throw new SystemException(ErrorMessage.RECORD_NOT_FOUND);

    return new PageImpl<>(
        customerMapper.toDTOList(pageContentList), pageable, customerPage.getTotalElements());
  }

  //@Transactional(propagation = Propagation.REQUIRED)
  public void addCustomer(CustomerDTO customerDTO) throws IOException, JAXBException, SOAPException {

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("text/plain");

    RequestBody body = RequestBody.create(mediaType, XMLConverter.dtoToXML(customerDTO,"add"));
    Request request = new Request.Builder()
            .url("http://localhost:8080/SOAPCustomer/services/CustomerServiceImpl?wsdl")
            .method("POST", body)
            .addHeader("Content-Type", "text/plain")
            .build();

    Response response = client.newCall(request).execute();



  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void editCustomer(CustomerDTO customerDTO) throws JAXBException, IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("text/plain");

    RequestBody body = RequestBody.create(mediaType, XMLConverter.dtoToXML(customerDTO,"edit"));
    Request request = new Request.Builder()
            .url("http://localhost:8080/SOAPCustomer/services/CustomerServiceImpl?wsdl")
            .method("POST", body)
            .addHeader("Content-Type", "text/plain")
            .build();

    Response response = client.newCall(request).execute();
    //customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO));
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void deleteCustomer(int customerId) throws JAXBException, IOException {

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("text/plain");

    RequestBody body = RequestBody.create(mediaType, XMLConverter.idToXML(customerId));
    Request request = new Request.Builder()
            .url("http://localhost:8080/SOAPCustomer/services/CustomerServiceImpl?wsdl")
            .method("POST", body)
            .addHeader("Content-Type", "text/plain")
            .build();
    Response response = client.newCall(request).execute();
   // customerRepository.deleteById(customerId);
  }

  public CustomerDTO getCustomerById(int customerId) {

    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (!customerOptional.isPresent())
      throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    return customerMapper.toDTO(customerOptional.get());
  }

  public Page<CustomerDTO> getCustomersPageByName(String customerName, Pageable pageable) {

    Page<Customer> customerPage =
        customerRepository.findCustomersByNameContains(customerName, pageable);
    List<Customer> pageContentList = customerPage.getContent();

    if (pageContentList.isEmpty()) throw new RecordNotFoundException(ErrorMessage.RECORD_NOT_FOUND);

    return new PageImpl<>(
        customerMapper.toDTOList(pageContentList), pageable, customerPage.getTotalElements());
  }
}
