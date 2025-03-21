package com.efekansalman.Library.service;

import java.util.List;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Category;

public interface BookService {

	// Adds a new book to the library
	Book addBook(Book book);
	
	// Removes a book by ID
	void removeBook(Long bookId);
	
	// Finds books by title 
	List<Book> findBooksByTitle(String title);
	
	// Finds books by category
    List<Book> findBooksByCategory(Category category);
	
    // Finds all available books
	List<Book> findAvailableBooks();
	
}
