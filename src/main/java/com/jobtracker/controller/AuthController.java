package com.jobtracker.controller;

import com.jobtracker.dto.AuthRequest;
import com.jobtracker.dto.AuthResponse;
import com.jobtracker.model.User;
import com.jobtracker.repository.UserRepository;
import com.jobtracker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	// Register a new user
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Username already exists.");
		}

		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role("USER")
				.build();

		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully");
	}

	// Login and return JWT
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);

		String token = jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
