package com.efekansalman.Library.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id; 
    private Long customerId;
    private String customerUsername; 
    private String message; 
    private Date sentDate; 
    private boolean isRead; 
}
