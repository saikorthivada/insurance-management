package com.insuranceManagement.demo.model;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // UUID will be generated automatically
    @JdbcTypeCode(SqlTypes.UUID)  // This maps the UUID to the SQL UUID type
    private UUID id;
	
	@NotNull()
	private String agentID;
	
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
	    
	    @NotBlank()
	    private String dateOfBirth;
	    
	    
	    @NotBlank()
	    private String occupation;
	    
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
	    
	    public void setEmail(String emailInput) {
	    	this.email = emailInput;
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
	    
	    public void setDateOfBirth(String inputDateOfBirth) {
	    	this.dateOfBirth = inputDateOfBirth;
	    }
	
	    public String getDateOfBirth() {
	    	return this.dateOfBirth;
	    }
	    
	    public void setOccupation(String inputOccupation) {
	    	this.occupation = inputOccupation;
	    }
	    
	    public String getOccupation() {
	    	return this.occupation;
	    }
	    
	    public UUID getId() {
	    	return this.id;
	    }
	    
	    public void setAgentId(String inputAgentId) {
	    	this.agentID = inputAgentId;
	    }
	    
	    public String getAgentID() {
	    	return this.agentID;
	    }
}
