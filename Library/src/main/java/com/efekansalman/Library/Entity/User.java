package com.efekansalman.Library.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;




@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final long id;
	
	@Column(nullable = false, unique = true)
	private final String username;
	
	@Column(nullable = false)
	private final String password;
	
	@Column(nullable = false)
	private final String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	private Double penaltyDebt;
}
