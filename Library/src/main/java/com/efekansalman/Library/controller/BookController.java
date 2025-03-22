package com.efekansalman.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Category;
import com.efekansalman.Library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		// Adds a new book
		Book addedBook = bookService.addBook(book);
		return ResponseEntity.ok(addedBook);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeBook(@PathVariable Long id) {
		// Removes a book
		bookService.removeBook(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search/title")
	public ResponseEntity<List<Book>> findBooksByTitle(@RequestParam String title) {
		// Finds book by title
		List<Book> books = bookService.findBooksByTitle(title);
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/search/category")
	public ResponseEntity<List<Book>> findBooksByCategory(@RequestParam Category category) {
		// Finds books by category
		List<Book> books = bookService.findBooksByCategory(category);
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Book>> findAvailableBooks() {
		// Finds all available books
		List<Book> books = bookService.findAvailableBooks();
		return ResponseEntity.ok(books);
	}	
}
