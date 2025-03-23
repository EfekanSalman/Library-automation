package com.efekansalman.Library.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Lending;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {

	// Finds all lendings for a specific customer
	List<Lending> findByCustomer(Customer customer);
	
	// Finds overdue lendings 
	List<Lending> findByReturnDateIsNullAndDueDateBefore(Date date, Pageable pageable);
}
