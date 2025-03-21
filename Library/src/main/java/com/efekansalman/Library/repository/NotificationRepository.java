package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	// Finds all unread notifications for a customer
	List<Notification> findByCustomerAndIsReadFalse(Customer customer);
}
