package com.library.service;

import com.library.entity.Book;
import com.library.mapper.BookMapper;
import com.library.exception.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) { this.bookMapper = bookMapper; }

    public List<Book> getAll() { return bookMapper.getAll(); }
    public Book getById(Long id) {
        return bookMapper.getById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
    }
    public Book findByIsbn(String isbn) {
        return bookMapper.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book with isbn " + isbn + " not found"));
    }
    public void create(Book book) { bookMapper.insert(book); }
    public void update(Book book) { bookMapper.update(book); }
    public void delete(Long id) { bookMapper.delete(id); }
}
