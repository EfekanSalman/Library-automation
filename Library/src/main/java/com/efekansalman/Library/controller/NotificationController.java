package com.efekansalman.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Notification;
import com.efekansalman.Library.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<Notification> sendNotification(@RequestParam Long customerId, @RequestParam String message) {
		// Sends a notification to customer
        Notification notification = notificationService.sendNotification(customerId, message);
        return ResponseEntity.ok(notification);
	}
	
	@GetMapping("/unread/{customerId}")
	public ResponseEntity<List<Notification>> findUnreadNotifications(@PathVariable Long customerId) {
        // Finds all unread notifications for a customer
        List<Notification> notifications = notificationService.findUnreadNotifications(customerId);
        return ResponseEntity.ok(notifications);
	}	
}
