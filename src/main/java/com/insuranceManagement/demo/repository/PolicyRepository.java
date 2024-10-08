package com.insuranceManagement.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.demo.model.PolicyDetails;

public interface PolicyRepository extends JpaRepository<PolicyDetails, UUID> {
	
	List<PolicyDetails> findByCustomerID(UUID customerID);

}
