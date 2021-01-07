package com.restaurantapi.restaurantapi.convertor;

import com.restaurantapi.restaurantapi.dto.CustomerDTO;
import com.restaurantapi.restaurantapi.dto.ResponseDTO;
import okhttp3.*;
import org.apache.xmlbeans.impl.soap.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;

public class XMLConverter {
  public static ResponseDTO xmlToDTO(String xml) throws JAXBException, IOException, SOAPException {

        final InputStream inputStream =
            new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));
        final SOAPMessage message = MessageFactory.newInstance().createMessage();
        final SOAPPart sp = message.getSOAPPart();
        final SOAPEnvelope env = sp.getEnvelope();
        final SOAPBody body = env.getBody();
        final JAXBContext jaxbContext = JAXBContext.newInstance(ResponseDTO.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (ResponseDTO) jaxbUnmarshaller.unmarshal(body.getOwnerDocument());
//    JAXBContext jaxbContext = JAXBContext.newInstance(ResponseDTO.class);
//
//    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//
//    // CustomerDTO customerDTO = (CustomerDTO) jaxbUnmarshaller.unmarshal(new StringReader(xml));
//
//    ResponseDTO responseDTO = (ResponseDTO) jaxbUnmarshaller.unmarshal(new StringReader(xml));
//
//    System.out.println(responseDTO);
//    return responseDTO;
  }

  public static String dtoToXML(CustomerDTO customerDTO, String type) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDTO.class);
    Marshaller marshaller = jaxbContext.createMarshaller();

    marshaller.setProperty("jaxb.fragment", Boolean.TRUE);

    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(customerDTO, stringWriter);
    String soapEnvelope = "";
    if (type.equals("add")) {
      soapEnvelope =
          "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\n"
              + "   <soapenv:Header/>\n"
              + "   <soapenv:Body>\n"
              + "      <ser:addCustomer>\n"
              + stringWriter.toString()
              + "      </ser:addCustomer>\n"
              + "   </soapenv:Body>\n"
              + "</soapenv:Envelope>";
    }
    if (type.equals("edit")) {
      soapEnvelope =
          "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\n"
              + "   <soapenv:Header/>\n"
              + "   <soapenv:Body>\n"
              + "      <ser:editCustomer>\n"
              + stringWriter.toString()
              + "      </ser:editCustomer>\n"
              + "   </soapenv:Body>\n"
              + "</soapenv:Envelope>";
    }

    return soapEnvelope;
  }

  public static String idToXML(int customerId) throws JAXBException {
    String soapEnvelope =
        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\n"
            + "   <soapenv:Header/>\n"
            + "   <soapenv:Body>\n"
            + "      <ser:deleteCustomer>\n"
            + "<ser:customerId>"
            + customerId
            + "</ser:customerId>"
            + "</ser:deleteCustomer>\n"
            + "   </soapenv:Body>\n"
            + "</soapenv:Envelope>";

    return soapEnvelope;
  }

  public static String pageToXML(int size, int pageNumber, String name) throws JAXBException {
    String soapEnvelope = "";
    if (name.equals("")) {
      soapEnvelope =
          "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\n"
              + "   <soapenv:Header/>\n"
              + "   <soapenv:Body>\n"
              + "      <ser:getCustomersPage>\n"
              + " <ser:pageDto> <xsd:name>"
              + name
              + "</xsd:name>"
              + "<ser:size>"
              + size
              + "</ser:size>"
              + "<ser:pageNumber>"
              + pageNumber
              + "</ser:pageNumber>"
              + "</ser:pageDto>"
              + "</ser:getCustomersPage>\n"
              + "   </soapenv:Body>\n"
              + "</soapenv:Envelope>";
    }
    if (!name.equals("")) {}

    return soapEnvelope;
  }
}
