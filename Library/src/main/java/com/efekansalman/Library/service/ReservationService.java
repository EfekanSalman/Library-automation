package com.efekansalman.Library.service;

import java.util.List;
import com.efekansalman.Library.Entity.Reservation;

public interface ReservationService {
	// Reserves a book for a customer
	Reservation reserveBook(Long bookId, Long customerId);
	
	// Cancels a reservations
	void cancelReservation(Long reservationId);

	// Finds reservations by customer
	List<Reservation> findReservationsByCustomer(Long customerId);
}
