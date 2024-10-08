package com.insuranceManagement.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceManagement.demo.model.Customer;
import com.insuranceManagement.demo.model.User;
import com.insuranceManagement.demo.service.CustomerService;
import com.insuranceManagement.demo.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
    private UserService userService;

	@Autowired
	private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
        try {
        	User user = userService.getUserById(UUID.fromString(customer.getAgentID()));
        	
        	if (user != null) {
        		 Customer registeredCustomer = customerService.registerCustomer(customer);
                 return ResponseEntity.ok(registeredCustomer);
        	}
        	 return ResponseEntity.status(404).body("No Agent found");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
    
    @GetMapping("/{agentID}")
    public ResponseEntity<?> getAllUsers(@PathVariable String agentID) {
    	 try {
    		 User user = userService.getUserById(UUID.fromString(agentID));
    		 if (user != null) {
    			 List<Customer> customerlist = customerService.getCustomerByAgentID(agentID);
    			 return ResponseEntity.ok(customerlist);
    		 }
    		 return ResponseEntity.status(404).body("No Agent found"); 
             
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
    
    @GetMapping("/{agentID}/{id}")
    public ResponseEntity<?> getAllUsers(@PathVariable String agentID, @PathVariable String id) {
    	 try {
    		 User user = userService.getUserById(UUID.fromString(agentID));
    		 if (user != null) {
    			 List<Customer> customerDetails = customerService.getCustomerDetails(id);
    			 return ResponseEntity.ok(customerDetails);
    		 }
    		 return ResponseEntity.status(404).body("No Agent found"); 
             
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
    
    @PutMapping("/{agentID}/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String agentID,@PathVariable String id, @RequestBody Customer customer) {
    	try { 
    	User userResponse = userService.getUserById(UUID.fromString(agentID));
		 if (userResponse != null) {
			 Customer customerDetails = customerService.updateCustomer(UUID.fromString(id), customer);
			 return ResponseEntity.ok(customerDetails);
		 }
		 return ResponseEntity.status(404).body("No Agent found"); 
    }catch (MessagingException e) {
        return ResponseEntity.status(500).body("Failed to send email");
    }
    }
    
    @DeleteMapping("/{agentID}/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable String agentID, @PathVariable String id) {
    	 try {
             Customer customerDetails = customerService.deleteByCustomerID(UUID.fromString(id));
             return ResponseEntity.ok(customerDetails);
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
}
