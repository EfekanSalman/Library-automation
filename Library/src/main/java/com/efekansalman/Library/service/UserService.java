package com.efekansalman.Library.service;

import java.util.Optional;

import com.efekansalman.Library.Entity.User;

public interface UserService {

	// Register a new user (admin/customer)
	User registerUser(User user);
	
	// Finds a user by username for login
	Optional<User> findByUsername(String username);
	
	// Updates user's penalty debt
	void updatePenaltDebt(Long userId, double amount);
}
