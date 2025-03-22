package com.efekansalman.Library.dto;

import com.efekansalman.Library.Entity.Role;
import lombok.Data;
@Data
public class UserDTO {
	private Long id;
	private String username;
	private String email;
	private Role role;
	private double penaltyDebt;
}
