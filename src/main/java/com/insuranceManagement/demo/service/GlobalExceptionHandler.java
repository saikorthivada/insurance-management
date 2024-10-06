package com.insuranceManagement.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GlobalException> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    	GlobalException response = new GlobalException(400, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // HTTP 400 Bad Request        
    }

}

