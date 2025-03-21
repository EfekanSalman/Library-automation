package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

	// Finds all donations by a specific customer
	List<Donation> findByCustomer(Customer customer);
}
