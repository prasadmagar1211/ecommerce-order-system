package com.example.main.controllers;

import com.example.main.models.Order;
import com.example.main.models.OrderRequest;
import com.example.main.models.User;
import com.example.main.repositories.OrderRepository;
import com.example.main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	// 1. PLACE ORDER: Updated to include Product Details and Address
	@PostMapping("/place")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
		try {
			Order order = new Order();
			order.setOrderDate(LocalDateTime.now());
			order.setStatus("PENDING");

			// Map basic order data
			order.setTotalAmount(request.getTotalAmount());

			// MAP THE NEW FIELDS FROM THE REQUEST
			order.setProductName(request.getProductName());
			order.setProductImage(request.getProductImage());
			order.setAddress(request.getAddress());
			order.setQuantity(request.getQuantity());

			// Find the user by ID
			User user = userRepository.findById(request.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));

			order.setUser(user);

			Order savedOrder = orderRepository.save(order);
			return ResponseEntity.ok(savedOrder);
		} catch (Exception e) {
			e.printStackTrace(); // Helpful for debugging in the console
			return ResponseEntity.badRequest().build();
		}
	}

	// 2. GET USER HISTORY: Used by the user to see "My Orders"
	@GetMapping("/user/{userId}")
	public List<Order> getUserOrders(@PathVariable Long userId) {
		return orderRepository.findByUserId(userId);
	}
}