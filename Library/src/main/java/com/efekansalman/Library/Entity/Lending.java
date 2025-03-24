package com.efekansalman.Library.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lendings")
public class Lending {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Customer customer;

	private final Date borrowDate;
	
	private final Date dueDate;
	
	private final Date returnDate;
	
	private final double fineAmount;
		
}
