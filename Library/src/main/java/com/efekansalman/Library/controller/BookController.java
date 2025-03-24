package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Book;
import com.efekansalman.Library.Entity.Category;
import com.efekansalman.Library.dto.BookDTO;
import com.efekansalman.Library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;
	
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setPublicationDate(bookDTO.getPublicationDate());
		book.setPrintDate(bookDTO.getPrintDate());
		book.setPageCount(bookDTO.getPageCount());
		book.setCategory(bookDTO.getCategory());
		book.setShelf(bookDTO.getShelf());
		book.setLibrary(bookDTO.getLibrary());
		book.setFloor(bookDTO.getFloor());
		book.setSection(bookDTO.getSection());
		book.setStock(bookDTO.getStock());
		book.setAvailable(bookDTO.isAvailable());
		
		// Save the book
		Book savedBook = bookService.addBook(book);
		
		// Convert Entity to DTO
		BookDTO savedBookDTO = convertToDTO(savedBook);
		return ResponseEntity.ok(savedBookDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeBook(@PathVariable Long id) {
		// Removes a book
		bookService.removeBook(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search/title")
	public ResponseEntity<List<BookDTO>> findBooksByTitle(@RequestParam String title) {
		// Finds book by title
		List<Book> books = bookService.findBooksByTitle(title);
		List<BookDTO> bookDTOs = books.stream().map(this::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(bookDTOs);
	}
	
	@GetMapping("/search/category")
	public ResponseEntity<List<BookDTO>> findBooksByCategory(@RequestParam Category category) {
		// Finds books by category
		List<Book> books = bookService.findBooksByCategory(category);
        List<BookDTO> bookDTOs = books.stream().map(this::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(bookDTOs);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<BookDTO>> findAvailableBooks() {
		// Finds all available books
        List<Book> books = bookService.findAvailableBooks();
        List<BookDTO> bookDTOs = books.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
	}	
	
    // Helper method to convert Book entity to BookDTO
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setPrintDate(book.getPrintDate());
        dto.setPageCount(book.getPageCount());
        dto.setCategory(book.getCategory());
        dto.setShelf(book.getShelf());
        dto.setLibrary(book.getLibrary());
        dto.setFloor(book.getFloor());
        dto.setSection(book.getSection());
        dto.setStock(book.getStock());
        dto.setAvailable(book.isAvailable());
        dto.setAuthorNames(book.getAuthors().stream().map(author -> author.getName()).collect(Collectors.toList()));
        return dto;
    }
}
