package com.efekansalman.Library.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String message;
	private String details;
	private LocalDateTime timestamp;
	
	public ErrorResponse(String message, String details) {
		this.message = message;
		this.details = details;
		this.timestamp = LocalDateTime.now();
	}
}
