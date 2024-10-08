package com.insuranceManagement.demo.controller;

import java.util.List;
import java.util.Optional;
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

import com.insuranceManagement.demo.model.PolicyDetails;
import com.insuranceManagement.demo.service.PolicyService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policy/{agentId}/{cutomerId}")
public class PolicyController {

	
	@Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<?> registerPolicy(@PathVariable String agentId, @PathVariable String cutomerId,@Valid @RequestBody PolicyDetails policyDetails) {
    	try {

        			policyDetails.setCustomerID(UUID.fromString(cutomerId));
        			PolicyDetails registeredCustomer = policyService.registerPolicy(agentId, cutomerId, policyDetails);
                    return ResponseEntity.ok(registeredCustomer);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getPoliciesByCustomerID(@PathVariable String agentId, @PathVariable String cutomerId) {
    	try {

        			List<PolicyDetails> policyCustomers = policyService.getAllPolicyDetailsByCustomerID(agentId, cutomerId);
                    return ResponseEntity.ok(policyCustomers);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
    
    @GetMapping("/{policyId}")
    public ResponseEntity<?> getPoliciesByPolicyId(@PathVariable String agentId, @PathVariable String cutomerId, @PathVariable String policyId) {
    	try {

        			PolicyDetails policyCustomers = policyService.getPolicyDetailsById(agentId, cutomerId, policyId);
                    return ResponseEntity.ok(policyCustomers);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
    
    @DeleteMapping("/{policyId}")
    public ResponseEntity<?> deletePolicyByPolicyID(@PathVariable String agentId, @PathVariable String cutomerId, @PathVariable String policyId) {
    	try {

        			PolicyDetails policyCustomers = policyService.deletePolicyById(agentId, cutomerId, policyId);
                    return ResponseEntity.ok(policyCustomers);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
    
    @PutMapping("/{policyId}")
    public ResponseEntity<?> updatePolicyDetails(@PathVariable String agentId, @PathVariable String cutomerId, @PathVariable String policyId, @Valid @RequestBody PolicyDetails policyDetails) {
    	try {

        			PolicyDetails policyCustomers = policyService.updatePolicy(agentId, cutomerId, policyId, policyDetails);
                    return ResponseEntity.ok(policyCustomers);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to Create Customer");
        }
    }
}
