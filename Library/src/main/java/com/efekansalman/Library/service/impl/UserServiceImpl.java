package com.efekansalman.Library.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.User;
import com.efekansalman.Library.exception.UserAlreadyExistsException;
import com.efekansalman.Library.repository.UserRepository;
import com.efekansalman.Library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
		
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(User user) {
		
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("Email already exists: " + user.getEmail());
		}
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new UserAlreadyExistsException("Username already exists: " + user.getUsername());
		}
		
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
