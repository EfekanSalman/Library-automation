package com.efekansalman.Library.service;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Lending;
import com.efekansalman.Library.exception.InsufficientStockException;
import com.efekansalman.Library.exception.ResourceNotFoundException;
import com.efekansalman.Library.repository.BookRepository;
import com.efekansalman.Library.repository.LendingRepository;
import com.efekansalman.Library.repository.UserRepository;
import com.efekansalman.Library.service.impl.LendingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LendingServiceImplTest {

    @Mock
    private LendingRepository lendingRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LendingServiceImpl lendingService;

    private Book book;
    private Customer customer;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setStock(1);
        book.setAvailable(true);

        customer = new Customer();
        customer.setId(1L);
        customer.setUsername("testcustomer");
    }

    @Test
    void borrowBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(userRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(lendingRepository.save(any(Lending.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(bookRepository.save(book)).thenReturn(book);

        Lending lending = lendingService.borrowBook(1L, 1L);

        assertNotNull(lending);
        assertEquals(book, lending.getBook());
        assertEquals(customer, lending.getCustomer());
        assertEquals(0, book.getStock());
        assertFalse(book.isAvailable());
        verify(lendingRepository, times(1)).save(any(Lending.class));
    }

    @Test
    void borrowBook_InsufficientStock_ThrowsException() {
        book.setStock(0);
        book.setAvailable(false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(userRepository.findById(1L)).thenReturn(Optional.of(customer));

        InsufficientStockException exception = assertThrows(InsufficientStockException.class, () -> {
            lendingService.borrowBook(1L, 1L);
        });

        assertEquals("Book is not available or out of stock: Test Book", exception.getMessage());
        verify(lendingRepository, never()).save(any(Lending.class));
    }

    @Test
    void returnBook_Success() {
        Lending lending = new Lending();
        lending.setId(1L);
        lending.setBook(book);
        lending.setCustomer(customer);
        lending.setBorrowDate(new Date());
        lending.setDueDate(new Date());

        when(lendingRepository.findById(1L)).thenReturn(Optional.of(lending));
        when(bookRepository.save(book)).thenReturn(book);
        when(lendingRepository.save(lending)).thenReturn(lending);

        lendingService.returnBook(1L);

        assertNotNull(lending.getReturnDate());
        assertEquals(1, book.getStock());
        assertTrue(book.isAvailable());
        verify(lendingRepository, times(1)).save(lending);
    }
}