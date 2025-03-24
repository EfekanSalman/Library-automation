package com.efekansalman.Library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Reservation;
import com.efekansalman.Library.Entity.Status;
import com.efekansalman.Library.repository.BookRepository;
import com.efekansalman.Library.repository.CustomerRepository;
import com.efekansalman.Library.repository.ReservationRepository;
import com.efekansalman.Library.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final BookRepository bookRepository;
	private final CustomerRepository customerRepository;
	
	public ReservationServiceImpl(ReservationRepository reservationRepository, BookRepository bookRepository,
			CustomerRepository customerRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.bookRepository = bookRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Reservation reserveBook(Long bookId, Long customerId) {
		// Find the book and customer
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
		Customer customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		
		// Create a new reservation
		Reservation reservation = new Reservation();
		reservation.setBook(book);
		reservation.setCustomer(customer);
		reservation.setReservationDate(new Date());
		reservation.setStatus(Status.PENDING);
		
		// Save and return the reservation
		return reservationRepository.save(reservation);
	}

	@Override
	public void cancelReservation(Long reservationId) {
		// Find the reservation
		Reservation reservation = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + reservationId));
		
		// Update status to CANCELLED
		reservation.setStatus(Status.CANCELLED);
		reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> findReservationsByCustomer(Long customerId) {
		// Find the customer
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		// Return all reservations for the customer
		return reservationRepository.findByCustomer(customer);
	}

}
