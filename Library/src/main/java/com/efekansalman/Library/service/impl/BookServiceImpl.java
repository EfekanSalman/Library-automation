package com.efekansalman.Library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Category;
import com.efekansalman.Library.repository.BookRepository;
import com.efekansalman.Library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	// TODO autowired can be deleted?
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book addBook(Book book) {
		// Save the book to the database and return it
		return bookRepository.save(book);
	}

	@Override
	public void removeBook(Long bookId) {
		// Delete the book by ID
		bookRepository.deleteById(bookId);	
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		// Find books with titles containing the given string
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<Book> findBooksByCategory(Category category) {
		// Find books by category
	       return bookRepository.findByCategory(category);
	}

	@Override
	public List<Book> findAvailableBooks() {
		// Find all books that are available for borrowing
		return bookRepository.findByIsAvailableTrue();
	}

}
