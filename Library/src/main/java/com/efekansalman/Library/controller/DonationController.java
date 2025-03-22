package com.efekansalman.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Donation;
import com.efekansalman.Library.service.DonationService;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

	@Autowired
	private DonationService donationService;
	
	@PostMapping
	public ResponseEntity<Donation> donateBook(@RequestParam Long bookId, @RequestParam Long customerId) {
		// Records a book donation
		Donation donation = donationService.donateBook(bookId, customerId);
		return ResponseEntity.ok(donation);
	}
	
	public ResponseEntity<List<Donation>> findDonationsByCustomer(@PathVariable Long customerId) {
		// Finds all donations by a customer
		List<Donation> donations = donationService.findDonationsByCustomer(customerId);
		return ResponseEntity.ok(donations);	
	}
}
