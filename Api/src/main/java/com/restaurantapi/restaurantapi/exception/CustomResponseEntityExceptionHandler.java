package com.restaurantapi.restaurantapi.exception;

import com.restaurantapi.restaurantapi.dto.ErrorMessage;
import com.restaurantapi.restaurantapi.dto.ErrorResponseDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings({"unchecked", "rawtypes"})
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
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage(e.getMessage(), null, locale), e.getErrorCode(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingSystemException(SystemException e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage(e.getMessage(), null, locale), e.getErrorCode(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingConstraintViolationException(Exception e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(e.getMessage(), 0, request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handlingRecordNotFoundException(RecordNotFoundException e, WebRequest request, Locale locale) {
        ErrorResponseDTO response = prepareDTO(messageSource.getMessage(e.getMessage(), null, locale), e.getErrorCode(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errors = "";
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors += error.getDefaultMessage();
        }
        ErrorResponseDTO error = prepareDTO(errors, 0, request);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


    private ErrorResponseDTO prepareDTO(String message, int errorCode, WebRequest request) {
        return new ErrorResponseDTO(new Date(), message, request.getDescription(false), errorCode);
    }

}
