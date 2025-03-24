package com.efekansalman.Library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Notification;
import com.efekansalman.Library.repository.CustomerRepository;
import com.efekansalman.Library.repository.NotificationRepository;
import com.efekansalman.Library.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final CustomerRepository customerRepository;
	
	public NotificationServiceImpl(NotificationRepository notificationRepository,
			CustomerRepository customerRepository) {
		super();
		this.notificationRepository = notificationRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Notification sendNotification(Long customerId, String message) {
		// Find the customer
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		
		// Create a new notification
		Notification notification = new Notification();
		notification.setCustomer(customer);
		notification.setMessage(message);
		notification.setSentDate(new Date());
		notification.setRead(false);
		
		//save and return the notification
        return notificationRepository.save(notification);
	}

	@Override
	public List<Notification> findUnreadNotifications(Long customerId) {
		// Find the customer
		Customer customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new RuntimeException("Customer not fount with ID: " + customerId));
		// Return all unread notifications for the customer
		return notificationRepository.findByCustomerAndIsReadFalse(customer);
	}
}
