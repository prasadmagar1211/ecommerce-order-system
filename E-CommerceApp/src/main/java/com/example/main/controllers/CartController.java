package com.example.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.main.models.Product;
import com.example.main.models.User;
import com.example.main.repositories.UserRepository;
import com.example.main.repositories.ProductRepository;
import com.example.main.services.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*") // Allows your HTML file to talk to the Backend
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/add/{productId}")
	public ResponseEntity<String> addItemToCart(
	        @PathVariable Long productId, 
	        @RequestParam(defaultValue = "1") int quantity,
	        Authentication authentication) { // Changed from Principal to Authentication
	    
	    // 1. Check if login worked
	    if (authentication == null) {
	        return ResponseEntity.status(401).body("Not logged in");
	    }

	    String email = authentication.getName(); // This gets the email from the header
	    
	    User user = userRepository.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("User not found"));

		Product product = productRepository.findById(productId)
	        .orElseThrow(() -> new RuntimeException("Product not found"));

	    cartService.addToCart(user, product, quantity);

	    return ResponseEntity.ok("Success!");
	}
	
}