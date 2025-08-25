package com.library.mapper.dto;

import com.library.dto.BookDto;
import com.library.entity.Book;

public class BookMapperDTO {

    public static BookDto toDTO(Book book) {
        if (book == null) return null;
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.isAvailable()
        );
    }

    public static Book toEntity(BookDto dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setAvailable(dto.isAvailable());
        return book;
    }
}

