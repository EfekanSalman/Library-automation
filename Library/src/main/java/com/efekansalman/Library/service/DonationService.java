package com.efekansalman.Library.service;

import java.util.List;

import com.efekansalman.Library.Entity.Donation;

public interface DonationService {

	// Records a book donation by a customer
	Donation donateBook(Long bookId, Long customerId);
	
	// Finds all donations by a customer
	List<Donation> findDonationsByCustomer(Long customerId);
}
