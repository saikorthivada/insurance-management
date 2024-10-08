package com.insuranceManagement.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.demo.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    
    Optional<User> findByEmailAndPassword(String email, String password);

}

