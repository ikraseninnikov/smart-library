package com.library.controller;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.mapper.dto.BookMapperDTO;
import com.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll().stream()
                .map(BookMapperDTO::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return BookMapperDTO.toDTO(book);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getByIsbn(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return BookMapperDTO.toDTO(book);
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto bookDTO) {
        Book book = BookMapperDTO.toEntity(bookDTO);
        bookService.create(book);
        return BookMapperDTO.toDTO(book);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto bookDTO) {
        Book book = BookMapperDTO.toEntity(bookDTO);
        book.setId(id);
        bookService.update(book);
        return BookMapperDTO.toDTO(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
