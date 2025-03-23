package com.efekansalman.Library.service;

import com.efekansalman.Library.Entity.Customer;
import com.efekansalman.Library.Entity.Role;
import com.efekansalman.Library.exception.UserAlreadyExistsException;
import com.efekansalman.Library.repository.UserRepository;
import com.efekansalman.Library.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private Customer user; 

    @BeforeEach
    void setUp() {
        user = new Customer();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");
        user.setRole(Role.CUSTOMER);
    }

    @Test
    void registerUser_Success() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");
        when(userRepository.save(any(Customer.class))).thenReturn(user);

        Customer registeredUser = (Customer) userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertEquals("hashedPassword", registeredUser.getPassword());
        verify(userRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void registerUser_EmailAlreadyExists_ThrowsException() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registerUser(user);
        });

        assertEquals("Email already exists: test@example.com", exception.getMessage());
        verify(userRepository, never()).save(any(Customer.class));
    }

    @Test
    void findByUsername_Success() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<Customer> foundUser = Optional.empty();

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void updatePenaltyDebt_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Customer.class))).thenReturn(user);

        userService.updatePenaltyDebt(1L, 10.0);

        assertEquals(10.0, user.getPenaltyDebt());
        verify(userRepository, times(1)).save(user);
    }
}