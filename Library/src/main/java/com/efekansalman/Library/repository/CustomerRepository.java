package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// Finds all customer with a penalty debt greater than a specified amount
	List<Customer> findByPenaltyDebtGreaterThan(Double amount);
}
