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

import com.efekansalman.Library.Entity.Reservation;
import com.efekansalman.Library.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<Reservation> reserveBook(@RequestParam Long bookId, @RequestParam Long customerId) {
		// Reserves a book for a customer
		Reservation reservation = reservationService.reserveBook(bookId, customerId);
		return ResponseEntity.ok(reservation);
	} 
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Void> cancelReservation(@PathVariable long id) {
		// Cancels a reservation
		reservationService.cancelReservation(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Reservation>> findReservationsByCustomer(@PathVariable Long customerId) {
		// Finds all reservations for a customer
		List<Reservation> reservations = reservationService.findReservationsByCustomer(customerId);
		return ResponseEntity.ok(reservations);
	}	
}
