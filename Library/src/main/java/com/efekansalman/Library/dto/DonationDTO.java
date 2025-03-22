package com.efekansalman.Library.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DonationDTO {
    private Long id; 
    private Long bookId; 
    private String bookTitle; 
    private Long customerId; 
    private String customerUsername; 
    private Date donationDate;
}
