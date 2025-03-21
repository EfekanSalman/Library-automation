package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	// Finds authors by name
	List<Author> findByNameContainingIgnoreCase(String name);
	
}
