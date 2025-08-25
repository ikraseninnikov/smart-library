package com.library.service;

import com.library.entity.User;
import com.library.exception.NotFoundException;
import com.library.mapper.UserMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public User getById(Long id) {
        return userMapper.getById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    public void create(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }
}

