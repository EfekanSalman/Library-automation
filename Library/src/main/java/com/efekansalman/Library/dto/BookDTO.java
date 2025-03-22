package com.efekansalman.Library.dto;

import java.util.Date;
import java.util.List;

import com.efekansalman.Library.Entity.Category;

import lombok.Data;

@Data
public class BookDTO {
	private Long id;
	private String title;
	private Date publicationDate;
	private Date printDate;
	private int pageCount;
    private Category category;
	private String shelf;
	private String library;
	private int floor;
	private String section;
	private int stock;
	private boolean isAvailable;
	private List<String> authorNames;
}
