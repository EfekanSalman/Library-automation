package com.efekansalman.Library.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LendingDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long customerId; 
    private String customerUsername; 
    private Date borrowDate; 
    private Date dueDate; 
    private Date returnDate;
    private double fineAmount; 
}
