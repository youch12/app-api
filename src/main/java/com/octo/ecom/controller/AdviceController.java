package com.octo.ecom.controller;

import com.octo.ecom.exception.*;
import com.octo.ecom.utils.CustomMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
        ProductNotFoundException.class,
      })
  protected ResponseEntity<?> entityNotFound(Exception exception) {
        return new ResponseEntity<>(CustomMessage.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            value = {
                    BusinessException.class,
            })
    protected ResponseEntity<?> handleBusinessException(Exception exception) {
        return new ResponseEntity<>(CustomMessage.builder().status(HttpStatus.BAD_REQUEST).message(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(CustomMessage.builder().status(HttpStatus.BAD_REQUEST).message("Value not valid sent in the request").build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception) {
        CustomMessage customMessage = CustomMessage.builder().message(exception.getMessage().split(":")[1].trim()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(customMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> MethodArgumentTypeMismatchException(){
        CustomMessage customMessage = CustomMessage.builder().message("type of Field not valid sent in the url").status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(customMessage, HttpStatus.BAD_REQUEST);
    }
}
