package com.example.main.controllers;

import com.example.main.models.User;
import com.example.main.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody User user) {
		try {
			return ResponseEntity.ok(authService.signup(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: Email already exists.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Map<String, String> body) {
		try {
			User user = authService.login(body.get("email"), body.get("password"));
			// Return user object (excluding password for security)
			user.setPassword(null);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}
}