package com.example.main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.main.models.User;
import com.example.main.models.Order;
import com.example.main.repositories.UserRepository;
import com.example.main.repositories.OrderRepository; // 1. Make sure this import exists

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminUserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository; // 2. Add the repository here

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// 3. ADD THIS METHOD - This fixes the 404 error
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete user with active orders.");
		}
	}
}