package com.library.mapper;

import com.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
    List<Book> getAll();
    Optional<Book> getById(Long id);
    void insert(Book book);
    void update(Book book);
    void delete(Long id);
}
