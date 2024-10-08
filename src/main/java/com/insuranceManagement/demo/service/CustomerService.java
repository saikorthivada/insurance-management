package com.insuranceManagement.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceManagement.demo.model.Customer;
import com.insuranceManagement.demo.model.User;
import com.insuranceManagement.demo.repository.CustomerRepository;

import jakarta.mail.MessagingException;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) throws MessagingException {
        // Save the user in the database
    	Customer customerData = customerRepository.findByEmail(customer.getEmail());
    	if (customerData != null) {
    		throw new UserAlreadyExistsException("Customer already exist: " + customer.getEmail());
    	}
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }
    
    public List<Customer> getCustomerByAgentID(String agentID) throws MessagingException {
    	List<Customer> customerData = customerRepository.findByAgentID(agentID);

        return customerData;
    }
    
    public List<Customer> getCustomerDetails(String id) throws MessagingException {
    	List<Customer> customerData = customerRepository.findAllById(UUID.fromString(id));

        return customerData;
    }
    
    public Customer updateCustomer(UUID id, Customer updatedUserDetails) {
        // Find the user by id
        Optional<Customer> existingUserOptional = customerRepository.findById(id);
        
        if (existingUserOptional.isPresent()) {
            Customer existingUser = existingUserOptional.get();

            // Update the fields (based on the provided input)
            existingUser.setFirstName(updatedUserDetails.getFirstName());
            existingUser.setLastName(updatedUserDetails.getLastName());
            existingUser.setPhoneNumber(updatedUserDetails.getPhoneNumber());
            existingUser.setGender(updatedUserDetails.getGender());
            existingUser.setAddress(updatedUserDetails.getAddress());
            existingUser.setOccupation(updatedUserDetails.getOccupation());
            existingUser.setDateOfBirth(updatedUserDetails.getDateOfBirth());
            existingUser.setEmail(updatedUserDetails.getEmail());

            return customerRepository.save(existingUser);
        } else {
            throw new UserAlreadyExistsException("User not found with id: " + id);
        }
    }
    
    public Customer deleteByCustomerID(UUID id) throws MessagingException {
        // Save the user in the database
    	Optional<Customer> customer = customerRepository.findById(id);
    	
    	if (customer.isPresent()) {
    		customerRepository.deleteById(id);
            return customer.get();
    	}
    	throw new UserAlreadyExistsException("User does not exist: " + id);
    }
}
