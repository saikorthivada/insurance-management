package com.insuranceManagement.demo.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class PolicyDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // UUID will be generated automatically
    @JdbcTypeCode(SqlTypes.UUID)  // This maps the UUID to the SQL UUID type
    private UUID id;
	
	private UUID customerID;
	
	
	@NotNull()
	private String policyName;
	
	@NotNull()
	private String policyStartDate;
	
	@NotNull()
	private String policyEndDate;
	
	@NotNull()
	private int policyTenure;
	
	@NotNull()
	private String policyPremium;
	
	@NotNull()
	private int premiumAmount;
	
	
	public void setCustomerID(UUID inputCustomerID) {
		this.customerID = inputCustomerID;
	}
	
	public UUID getcustomerID() {
		return this.customerID;
	}
	
	public void setPolicyName(String inputPolicyName) {
		this.policyName = inputPolicyName;
	}
	
	public String getPolicyName() {
		return this.policyName;
	}
	
	public void setPolicyEndDate(String inputPolicyEndDate) {
		this.policyEndDate = inputPolicyEndDate;
	}
	
	public String getPolicyEndDate() {
		return this.policyEndDate;
	}
	
	public void setPolicyStartDate(String inputPolicyStartDate) {
		this.policyStartDate = inputPolicyStartDate;
	}
	
	public String getPolicyStartDate() {
		return this.policyStartDate;
	}
	
	public void setPolicyTenure(int inputPolicyTenure) {
		this.policyTenure = inputPolicyTenure;
	}
	
	public int getPolicyTenure() {
		return this.policyTenure;
	}
	
	public void setPolicyPremium(String inputPolicyPremium) {
		this.policyPremium = inputPolicyPremium;
	}
	
	public String getPolicyPremium() {
		return this.policyPremium;
	}
	
	public void setPremiumAmount(int inputPremiumAmount) {
		this.premiumAmount = inputPremiumAmount;
	}
	
	public int getpremiumAmount() {
		return this.premiumAmount;
	}
	
	public UUID getId() {
		return this.id;
	}
}
