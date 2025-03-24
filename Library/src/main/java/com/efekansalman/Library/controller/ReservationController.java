package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Reservation;
import com.efekansalman.Library.dto.ReservationDTO;
import com.efekansalman.Library.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
    private final ReservationService reservationService;
    
    public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@PostMapping
    public ResponseEntity<ReservationDTO> reserveBook(@RequestParam Long bookId, @RequestParam Long customerId) {
        // Reserve a book and convert to DTO
        Reservation reservation = reservationService.reserveBook(bookId, customerId);
        ReservationDTO reservationDTO = convertToDTO(reservation);
        return ResponseEntity.ok(reservationDTO);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        // Cancel a reservation (no DTO needed for this simple operation)
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationDTO>> findReservationsByCustomer(@PathVariable Long customerId) {
        // Find reservations and convert to DTO
        List<Reservation> reservations = reservationService.findReservationsByCustomer(customerId);
        List<ReservationDTO> reservationDTOs = reservations.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(reservationDTOs);
    }

    // Helper method to convert Reservation entity to ReservationDTO
    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setBookId(reservation.getBook().getId());
        dto.setBookTitle(reservation.getBook().getTitle());
        dto.setCustomerId(reservation.getCustomer().getId());
        dto.setCustomerUsername(reservation.getCustomer().getUsername());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

}
