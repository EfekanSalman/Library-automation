package com.efekansalman.Library.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.User;
import com.efekansalman.Library.repository.UserRepository;
import com.efekansalman.Library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// TODO people don't use "autowired" annotations so ı can delete that later
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public User registerUser(User user) {
		// Save the user to the database and return it
		user.setPassword(passwordEncoder.encode(user.getPassword())); // password will be hashed
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// Find a user by username using the repository
		return userRepository.findByUsername(username);
	}

	@Override
	public void updatePenaltyDebt(Long userId, double amount) {
		// Find the user by ID, update penalty debt and save
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		user.setPenaltyDebt(user.getPenaltyDebt() + amount);
		userRepository.save(user);
	}

}
