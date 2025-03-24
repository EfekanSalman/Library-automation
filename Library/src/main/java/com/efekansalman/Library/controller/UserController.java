package com.efekansalman.Library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.User;
import com.efekansalman.Library.dto.UserDTO;
import com.efekansalman.Library.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
    public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        UserDTO dto = new UserDTO();
        dto.setId(registeredUser.getId());
        dto.setUsername(registeredUser.getUsername());
        dto.setEmail(registeredUser.getEmail());
        dto.setRole(registeredUser.getRole());
        dto.setPenaltyDebt(registeredUser.getPenaltyDebt());
        return dto;
    }

	
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        // Find user and convert to DTO
        return userService.findByUsername(username)
            .map(this::convertToDTO)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/penalty")
    public ResponseEntity<Void> updatePenaltyDebt(@PathVariable Long id, @RequestParam double amount) {
        // Update penalty debt (no DTO needed for this simple operation)
        userService.updatePenaltyDebt(id, amount);
        return ResponseEntity.ok().build();
    }

    // Helper method to convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPenaltyDebt(user.getPenaltyDebt());
        return dto;
    }
}