package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Donation;
import com.efekansalman.Library.dto.DonationDTO;
import com.efekansalman.Library.service.DonationService;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDTO> donateBook(@RequestParam Long bookId, @RequestParam Long customerId) {
        // Donate a book and convert to DTO
        Donation donation = donationService.donateBook(bookId, customerId);
        DonationDTO donationDTO = convertToDTO(donation);
        return ResponseEntity.ok(donationDTO);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<DonationDTO>> findDonationsByCustomer(@PathVariable Long customerId) {
        // Find donations and convert to DTO
        List<Donation> donations = donationService.findDonationsByCustomer(customerId);
        List<DonationDTO> donationDTOs = donations.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donationDTOs);
    }

    // Helper method to convert Donation entity to DonationDTO
    private DonationDTO convertToDTO(Donation donation) {
        DonationDTO dto = new DonationDTO();
        dto.setId(donation.getId());
        dto.setBookId(donation.getBook().getId());
        dto.setBookTitle(donation.getBook().getTitle());
        dto.setCustomerId(donation.getCustomer().getId());
        dto.setCustomerUsername(donation.getCustomer().getUsername());
        dto.setDonationDate(donation.getDonationDate());
        return dto;
    }
}
