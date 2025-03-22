package com.efekansalman.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Lending;
import com.efekansalman.Library.service.LendingService;

@RestController
@RequestMapping("/api/lendings")
public class LendingController {

	@Autowired
	private LendingService lendingService;
	
	@PostMapping("/borrow")
	public ResponseEntity<Lending> borrowBook(@RequestParam Long bookId, @RequestParam Long customerId) {
		// Borrows a book for a customer
		Lending lending = lendingService.borrowBook(bookId, customerId);
		return ResponseEntity.ok(lending);
	}
	
	@PutMapping("/return/{id}")
	public ResponseEntity<Void> returnBook(@PathVariable Long id) {
		// Returns a borrowed book
		lendingService.returnBook(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Lending>> findLendingsByCustomer(@PathVariable Long customerId) {
		// Finds all lendings for a customer
        List<Lending> lendings = lendingService.findLendingsByCustomer(customerId);
        return ResponseEntity.ok(lendings);
	}
	
	@GetMapping("/fine/{id}")
	public ResponseEntity<Double> calculateFine(@PathVariable Long id) {
		// Calculates the fine for a lending
		double fine = lendingService.calculateFine(id);
		return ResponseEntity.ok(fine);
	}
}
