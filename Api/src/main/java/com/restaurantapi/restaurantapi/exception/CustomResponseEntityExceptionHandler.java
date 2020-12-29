package com.restaurantapi.restaurantapi.exception;

import com.restaurantapi.restaurantapi.dto.ErrorResponseDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Locale;

@Service
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    public CustomResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessRuleException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingBusinessRuleException(BusinessRuleException e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage( e.getMessage(),null,locale),e.getErrorCode() ,request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingSystemException(SystemException e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage( e.getMessage(),null,locale),e.getErrorCode() , request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingRecordNotFoundException(RecordNotFoundException e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage( e.getMessage(),null,locale), e.getErrorCode(),request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDTO prepareDTO(String message,int errorCode, WebRequest request) {
        return new ErrorResponseDTO(new Date(), message, request.getDescription(false),errorCode);
    }
}
