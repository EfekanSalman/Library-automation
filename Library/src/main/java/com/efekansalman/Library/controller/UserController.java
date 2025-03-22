package com.efekansalman.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.User;
import com.efekansalman.Library.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUSer(@RequestBody User user) {
		// Register a new user
		User registerUser = userService.registerUser(user);
		return ResponseEntity.ok(registerUser);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		// Finds a user by username
		return userService.findByUsername(username)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}/penalty")
	public ResponseEntity<Void> updatePenaltyDebt(@PathVariable Long id, @RequestParam double amount) {
		// Updates the penalty debt for a user
		userService.updatePenaltDebt(id, amount);
		return ResponseEntity.ok().build();
	}	
}
