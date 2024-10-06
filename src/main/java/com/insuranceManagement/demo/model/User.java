package com.insuranceManagement.demo.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // UUID will be generated automatically
    @JdbcTypeCode(SqlTypes.UUID)  // This maps the UUID to the SQL UUID type
    private UUID id;

    @NotNull()
    private String firstName;

    @NotNull()
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "(0|91)?[6-9][0-9]{9}", message = "Invalid Indian phone number")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", message = "Invalid Password")
    private String password;

   
    // Getters and Setters
    public String getFirstName() {
    	return this.firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return this.lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPhoneNumber() {
    	return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public String getGender() {
    	return this.gender;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    
    public void setPassword(String passwordInput) {
    	this.password = passwordInput;
    }
    
    public UUID getId() {
    	return this.id;
    }

}

