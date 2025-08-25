package com.library.mapper;

import com.library.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<User> getAll();
    Optional<User> getById(Long id);
    void insert(User user);
    void update(User user);
    void delete(Long id);
}

