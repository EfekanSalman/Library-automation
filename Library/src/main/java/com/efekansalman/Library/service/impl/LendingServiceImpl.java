package com.efekansalman.Library.service.impl;


import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Lending;
import com.efekansalman.Library.repository.BookRepository;
import com.efekansalman.Library.repository.CustomerRepository;
import com.efekansalman.Library.repository.LendingRepository;
import com.efekansalman.Library.service.LendingService;

@Service
public class LendingServiceImpl implements LendingService {

	@Autowired
	private LendingRepository lendingRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public Lending borrowBook(Long bookId, Long customerId) {

		// Find the book and customer
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		
		// Check if the book is available
		if (!book.isAvailable() || book.getStock() <= 0) {
			throw new RuntimeException("Book is not available for borrowing");
		}
		
		// Create a new lending record
		Lending lending = new Lending();
		lending.setBook(book);
		lending.setCustomer(customer);
		lending.setBorrowDate(new Date());
		lending.setDueDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)); // 14 days from now
		lending.setFineAmount(0.0);

		// Update book availability and stock
		book.setStock(book.getStock() - 1);
		book.setAvailable(book.getStock() > 0);
		bookRepository.save(book);
	
		// Save and return the lending :)
		return lendingRepository.save(lending);
	}

	@Override
	public void returnBook(Long lendingId) {
		// Find the lending record
		Lending lending = lendingRepository.findById(lendingId)
				.orElseThrow(() -> new RuntimeException("Lending not found with ID: " + lendingId));
		
		// Update return date and book stock
		lending.setReturnDate(new Date());
		Book book = lending.getBook();
		book.setStock(book.getStock() + 1);
		book.setAvailable(true);
		
		// Save changes
		bookRepository.save(book);
		lendingRepository.save(lending);
	}

	@Override
	public List<Lending> findLendingsByCustomer(Long customerId) {
		// Find the customer
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		// Return all lendings for the customer
		return lendingRepository.findByCustomer(customer);
	}

	@Override
	public double calculateFine(Long lendingId) {
		// Find the lending record
		Lending lending = lendingRepository.findById(lendingId)
				.orElseThrow(() -> new RuntimeException("Lending not found with ID: " + lendingId));
		
		// If book is returned, no fine :) 
		if (lending.getReturnDate() != null) {
			return 0.0;
		}
		
		// Calculate days overdue
		long currentTime = System.currentTimeMillis();
		long dueTime = lending.getDueDate().getTime();
		
		if (currentTime <= dueTime) {
			return 0.0; 
		}
		
		long daysOverdue = (currentTime - dueTime) / (1000 * 60 * 60 *24);
		double finePerDay = 1.0; // $1 per day
		return daysOverdue * finePerDay;
	}
}
