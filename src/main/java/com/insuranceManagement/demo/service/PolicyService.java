package com.insuranceManagement.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.insuranceManagement.demo.model.Customer;
import com.insuranceManagement.demo.model.PolicyDetails;
import com.insuranceManagement.demo.model.User;
import com.insuranceManagement.demo.repository.PolicyRepository;
import com.insuranceManagement.demo.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Service
public class PolicyService {


	@Autowired
    private UserService userService;

	@Autowired
	private CustomerService customerService;
	
	 @Autowired
	 private PolicyRepository policyRepository;

	    public PolicyDetails registerPolicy(String agentId,String cutomerId,PolicyDetails policyDetails) throws MessagingException {
	        	User user = userService.getUserById(UUID.fromString(agentId));
	        	
	        	if (user != null) {
	        		
	        		List<Customer> customer = customerService.getCustomerDetails(cutomerId);
	        		
	        		if (!customer.isEmpty()) {
	        			policyDetails.setCustomerID(UUID.fromString(cutomerId));
	        			PolicyDetails savedPolicyDetails = policyRepository.save(policyDetails);
	        	        return savedPolicyDetails;
	        		}
	        		throw new UserAlreadyExistsException("User already exist: " + user.getEmail());
	        	}
	        	throw new UserAlreadyExistsException("User already exist: " + user.getEmail());
	    	
	    }
	    
	    public List<PolicyDetails> getAllPolicyDetailsByCustomerID(String agentId,String cutomerId) throws MessagingException {
	        
	    	User user = userService.getUserById(UUID.fromString(agentId));
        	
        	if (user != null) {
        		
        		List<Customer> customer = customerService.getCustomerDetails(cutomerId);
        		
        		if (!customer.isEmpty()) {
        			List<PolicyDetails> allPoliciesByCustomerID = policyRepository.findByCustomerID(UUID.fromString(cutomerId));

        	        return allPoliciesByCustomerID;
        		}
        		throw new UserAlreadyExistsException("User already exist: " + cutomerId);
        	}
        	throw new UserAlreadyExistsException("User already exist: " + agentId);
	        
	    }
	    
	    
	    public PolicyDetails getPolicyDetailsById(String agentId,String cutomerId, String policyId) throws MessagingException {
	    	
	    	User user = userService.getUserById(UUID.fromString(agentId));
        	
        	if (user != null) {
        		
        		List<Customer> customer = customerService.getCustomerDetails(cutomerId);
        		
        		if (!customer.isEmpty()) {
        			Optional<PolicyDetails> policyDetails = policyRepository.findById(UUID.fromString(policyId));
        	    	
        	    	if (policyDetails.isPresent()) {
        	            return policyDetails.get();
        	    	}
        	    	throw new UserAlreadyExistsException("Policy does not exist: " + policyId);
        		}
        		throw new UserAlreadyExistsException("Customer does not exist: " + cutomerId);
        	}
        	throw new UserAlreadyExistsException("User already exist: " + agentId);
	    	
	    }
	    
	    public PolicyDetails deletePolicyById(String agentId,String cutomerId, String policyId) throws MessagingException {
	    	
	    	User user = userService.getUserById(UUID.fromString(agentId));
        	
        	if (user != null) {
        		
        		List<Customer> customer = customerService.getCustomerDetails(cutomerId);
        		
        		if (!customer.isEmpty()) {
        			Optional<PolicyDetails> policyDetails = policyRepository.findById(UUID.fromString(policyId));
        	    	
        	    	if (policyDetails.isPresent()) {
        	    		policyRepository.deleteById(UUID.fromString(policyId));
        	            return policyDetails.get();
        	    	}
        	    	throw new UserAlreadyExistsException("Policy does not exist: " + policyId);
        		}
        		throw new UserAlreadyExistsException("Customer does not exist: " + cutomerId);
        	}
        	throw new UserAlreadyExistsException("User already exist: " + agentId);
	    	
	    }
	    
	    
	    public PolicyDetails updatePolicy(String agentId,String cutomerId, String policyId, PolicyDetails updatedPolicyDetails) throws MessagingException {

	    	User user = userService.getUserById(UUID.fromString(agentId));
        	
        	if (user != null) {
        		
        		List<Customer> customer = customerService.getCustomerDetails(cutomerId);
        		
        		if (!customer.isEmpty()) {
        			Optional<PolicyDetails> existingPolicyDetailsOptional = policyRepository.findById(UUID.fromString(policyId));
        	        
        	        if (existingPolicyDetailsOptional.isPresent()) {
        	            PolicyDetails existingPolicy = existingPolicyDetailsOptional.get();

        	            // Update the fields (based on the provided input)
        	            existingPolicy.setPolicyEndDate(updatedPolicyDetails.getPolicyEndDate());
        	            existingPolicy.setPolicyStartDate(updatedPolicyDetails.getPolicyStartDate());
        	            existingPolicy.setPolicyName(updatedPolicyDetails.getPolicyName());
        	            existingPolicy.setPolicyPremium(updatedPolicyDetails.getPolicyPremium());
        	            existingPolicy.setPolicyTenure(updatedPolicyDetails.getPolicyTenure());

        	            // Save the updated user details back to the database
        	            return policyRepository.save(existingPolicy);
        	        }
        	            throw new UserAlreadyExistsException("User not found with id: " + policyId);
        		}
        		throw new UserAlreadyExistsException("Customer does not exist: " + cutomerId);
        	}
        	throw new UserAlreadyExistsException("User already exist: " + agentId);
	    }
}
	    
