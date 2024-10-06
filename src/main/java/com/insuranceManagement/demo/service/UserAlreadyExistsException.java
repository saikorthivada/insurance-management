package com.insuranceManagement.demo.service;

public class UserAlreadyExistsException extends RuntimeException {
	
	public UserAlreadyExistsException(String message) {
        super(message);
    }

}
