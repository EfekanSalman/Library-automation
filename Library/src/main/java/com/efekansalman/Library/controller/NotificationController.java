package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Notification;
import com.efekansalman.Library.dto.NotificationDTO;
import com.efekansalman.Library.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}

	@PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestParam Long customerId, @RequestParam String message) {
        // Send a notification and convert to DTO
        Notification notification = notificationService.sendNotification(customerId, message);
        NotificationDTO notificationDTO = convertToDTO(notification);
        return ResponseEntity.ok(notificationDTO);
    }

    @GetMapping("/unread/{customerId}")
    public ResponseEntity<List<NotificationDTO>> findUnreadNotifications(@PathVariable Long customerId) {
        // Find unread notifications and convert to DTO
        List<Notification> notifications = notificationService.findUnreadNotifications(customerId);
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
    }

    // Helper method to convert Notification entity to NotificationDTO
    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setCustomerId(notification.getCustomer().getId());
        dto.setCustomerUsername(notification.getCustomer().getUsername());
        dto.setMessage(notification.getMessage());
        dto.setSentDate(notification.getSentDate());
        dto.setRead(notification.isRead());
        return dto;
    }
}
