package com.efekansalman.Library.dto;

import java.util.Date;

import com.efekansalman.Library.Entity.Status;

import lombok.Data;

@Data
public class ReservationDTO {
    private Long id;
    private Long bookId; 
    private String bookTitle;
    private Long customerId;
    private String customerUsername;
    private Date reservationDate; 
    private Status status; 
}
