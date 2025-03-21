package com.efekansalman.Library.service;

import java.util.List;

import com.efekansalman.Library.Entity.Lending;

public interface LendingService {

	// Borrows a book for a customer
	Lending borrowBook(Long bookId, Long customerId);
	
	// Returns a borrowed book
	void returnBook(Long lendingId);
	
	// Finds all lendings for a customer
	List<Lending> findLendingsByCustomer(Long customerId);
	
	// Calculates fine for overdue lendings
	double calculateFine(Long lendingId);
}
