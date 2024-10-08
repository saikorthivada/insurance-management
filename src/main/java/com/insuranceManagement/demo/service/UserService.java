package com.insuranceManagement.demo.service;

import com.insuranceManagement.demo.model.User;
import com.insuranceManagement.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws MessagingException {
        // Save the user in the database
    	User oneUser = userRepository.findByEmail(user.getEmail());
    	if (oneUser != null) {
    		throw new UserAlreadyExistsException("User already exist: " + user.getEmail());
    	}
        User savedUser = userRepository.save(user);

        return savedUser;
    }
    
    public List<User> getAllUsers() throws MessagingException {
        // Save the user in the database
        List<User> allUsers = userRepository.findAll();

        return allUsers;
    }
    
    public User getUserById(UUID id) throws MessagingException {
        // Save the user in the database
    	Optional<User> user = userRepository.findById(id);
    	
    	if (user.isPresent()) {
            return user.get();
    	}
    	throw new UserAlreadyExistsException("User does not exist: " + id);
    }
    
    public User deleteByUserID(UUID id) throws MessagingException {
        // Save the user in the database
    	Optional<User> user = userRepository.findById(id);
    	
    	if (user.isPresent()) {
    		userRepository.deleteById(id);
            return user.get();
    	}
    	throw new UserAlreadyExistsException("User does not exist: " + id);
    }
    
    public User updateUser(UUID id, User updatedUserDetails) {
        // Find the user by id
        Optional<User> existingUserOptional = userRepository.findById(id);
        
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update the fields (based on the provided input)
            existingUser.setFirstName(updatedUserDetails.getFirstName());
            existingUser.setLastName(updatedUserDetails.getLastName());
            existingUser.setPhoneNumber(updatedUserDetails.getPhoneNumber());
            existingUser.setGender(updatedUserDetails.getGender());
            existingUser.setAddress(updatedUserDetails.getAddress());

            // Save the updated user details back to the database
            return userRepository.save(existingUser);
        } else {
            throw new UserAlreadyExistsException("User not found with id: " + id);
        }
    }
    
    public Optional<User> login(String email, String password) {
    	Optional<User> user = userRepository.findByEmailAndPassword(email, password);
    	if (user != null) {
            return user;
    	}
    	throw new UserAlreadyExistsException("User does not exist: " + email);
    }
}

