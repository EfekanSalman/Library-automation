package com.efekansalman.Library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Donation;
import com.efekansalman.Library.repository.BookRepository;
import com.efekansalman.Library.repository.CustomerRepository;
import com.efekansalman.Library.repository.DonationRepository;
import com.efekansalman.Library.service.DonationService;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	private DonationRepository donationRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Donation donateBook(Long bookId, Long customerId) {
		// Find the book and customer
		 Book book = bookRepository.findById(bookId)
				 .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + bookId));
		
		// Create a new donation record
		Donation donation = new Donation();
		donation.setBook(book);
		donation.setCustomer(customer);
		donation.setDonationDate(new Date());
		
		// Increase the book's stock since it's donated
		book.setStock(book.getStock() + 1);
		book.setAvailable(true);
		bookRepository.save(book);
		
		// Save and return the donation
		return donationRepository.save(donation);
	}

	@Override
	public List<Donation> findDonationsByCustomer(Long customerId) {
		// Find the customer
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID:" + customerId));
		// Return all donations by the customer
		return donationRepository.findByCustomer(customer);
	}

}
