package com.insuranceManagement.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.demo.model.Customer;
import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, UUID>{
	Customer findByEmail(String email);
	
	List<Customer> findByAgentID(String agentID);

	List<Customer> findAllById(UUID fromString);
}
