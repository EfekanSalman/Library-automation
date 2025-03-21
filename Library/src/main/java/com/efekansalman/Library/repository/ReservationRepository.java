package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Reservation;
import com.efekansalman.Library.Entity.Status;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	// Finds reservation by customer
	List<Reservation> findByCustomer(Customer customer);
	
	// Finds reservations by book and status
	List<Reservation> findByBookAndStatus(Book book, Status status);
	
}
