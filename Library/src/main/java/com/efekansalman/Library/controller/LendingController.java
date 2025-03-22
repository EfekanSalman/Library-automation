package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Lending;
import com.efekansalman.Library.dto.LendingDTO;
import com.efekansalman.Library.service.LendingService;

@RestController
@RequestMapping("/api/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @PostMapping("/borrow")
    public ResponseEntity<LendingDTO> borrowBook(@RequestParam Long bookId, @RequestParam Long customerId) {
        // Borrow a book and convert to DTO
        Lending lending = lendingService.borrowBook(bookId, customerId);
        LendingDTO lendingDTO = convertToDTO(lending);
        return ResponseEntity.ok(lendingDTO);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        lendingService.returnBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LendingDTO>> findLendingsByCustomer(@PathVariable Long customerId) {
        // Find lendings and convert to DTO
        List<Lending> lendings = lendingService.findLendingsByCustomer(customerId);
        List<LendingDTO> lendingDTOs = lendings.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(lendingDTOs);
    }

    @GetMapping("/fine/{id}")
    public ResponseEntity<Double> calculateFine(@PathVariable Long id) {
        double fine = lendingService.calculateFine(id);
        return ResponseEntity.ok(fine);
    }

    // Helper method to convert Lending entity to LendingDTO
    private LendingDTO convertToDTO(Lending lending) {
        LendingDTO dto = new LendingDTO();
        dto.setId(lending.getId());
        dto.setBookId(lending.getBook().getId());
        dto.setBookTitle(lending.getBook().getTitle());
        dto.setCustomerId(lending.getCustomer().getId());
        dto.setCustomerUsername(lending.getCustomer().getUsername());
        dto.setBorrowDate(lending.getBorrowDate());
        dto.setDueDate(lending.getDueDate());
        dto.setReturnDate(lending.getReturnDate());
        dto.setFineAmount(lending.getFineAmount());
        return dto;
    }
}
