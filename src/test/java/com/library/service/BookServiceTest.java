package com.library.service;

import com.library.entity.Book;
import com.library.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setAvailable(true);

        book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Effective Java");
        book2.setAuthor("Joshua Bloch");
        book2.setAvailable(true);
    }

    @Test
    void testGetAllBooks() {
        when(bookMapper.getAll()).thenReturn(Arrays.asList(book1, book2));

        var books = bookMapper.getAll();
        assertEquals(2, books.size());
        verify(bookMapper, times(1)).getAll();
    }

    @Test
    void testGetBookById() {
        when(bookMapper.getById(1L)).thenReturn(Optional.of(book1));

        var book = bookService.getById(1L);
        assertNotNull(book);
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    void testCreateBook() {
        doNothing().when(bookMapper).insert(book1);

        bookService.create(book1);

        verify(bookMapper, times(1)).insert(book1);
    }

    @Test
    void testDeleteBook() {
        bookService.delete(book1.getId());
        verify(bookMapper, times(1)).delete(book1.getId());
    }
}
