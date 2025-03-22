package com.efekansalman.Library.Entity;

import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
		
	private Date publicationDate;
	
	private Date printDate;
	
	private int pageCount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;
	
	private String shelf;
	
	private String library;
	
	private int floor;
	
	private String section;
	
	private int stock;
	
	private boolean isAvailable;
	
	@ManyToMany
	@JoinTable (
			name = "book_authors",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id")
			)
		private List<Author> authors;
}
