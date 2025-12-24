package com.example.main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.main.models.User;
import com.example.main.repositories.UserRepository;
import com.example.main.services.UserService;

@RestController
@RequestMapping("/api/users") 
public class UserController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private UserService userService;

    UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    // This is for general use
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // This matches the path in your admin.html: http://localhost:8080/api/admin/users
    @GetMapping("/admin/list") 
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllCustomersForAdmin() {
        return userService.getAllUsers(); 
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return ResponseEntity.ok(userRepository.save(user)); 
    }
    
}