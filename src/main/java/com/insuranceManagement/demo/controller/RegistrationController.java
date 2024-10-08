package com.insuranceManagement.demo.controller;
import com.insuranceManagement.demo.model.ILoginRequest;
import com.insuranceManagement.demo.model.User;
import com.insuranceManagement.demo.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
    	 try {
             List<User> allUsers = userService.getAllUsers();
             return ResponseEntity.ok(allUsers);
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllUsers(@PathVariable UUID id) {
    	 try {
             User userDetails = userService.getUserById(id);
             return ResponseEntity.ok(userDetails);
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable UUID id) {
    	 try {
             User userDetails = userService.deleteByUserID(id);
             return ResponseEntity.ok(userDetails);
         } catch (MessagingException e) {
             return ResponseEntity.status(500).body("Failed to send email");
         }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable UUID id, @RequestBody User user) {
    	 User userDetails = userService.updateUser(id, user);
		 return ResponseEntity.ok(userDetails);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ILoginRequest loginDetails) {
   	 	Optional<User> userDetails = userService.login(loginDetails.getEmail(), loginDetails.getPassword());
		 return ResponseEntity.ok(userDetails);
   }
}
