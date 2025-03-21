package com.efekansalman.Library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Finds a user by username for login
	Optional<User> findByUsername(String username);
	
	// check if a user exits with the given email
	boolean existsByEmail(String email);
}
