package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Category;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	// Finds a books by title (case-insensitive partial match)
	List<Book> findByTitleContainingIgnoreCase(String title);
	
	// Finds book by category
	List<Book> findByCategory(Category category);
	
	// Finds available books
	List<Book> findByIsAvailableTrue();
	
}
