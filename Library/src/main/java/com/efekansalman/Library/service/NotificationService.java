package com.efekansalman.Library.service;

import java.util.List;

import com.efekansalman.Library.Entity.Notification;

public interface NotificationService {
	// Sends a notification to a customer
	Notification sendNotification(Long customerId, String message);
	
	// Finds all unread notifications for a customer
	List<Notification> findUnreadNotifications(Long customerId);
}
