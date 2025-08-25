package com.library.service;

import com.library.entity.User;
import com.library.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");
        user1.setEmail("alice@test.com");
        user1.setPasswordHash("hash1");
        user1.setCreatedAt(LocalDateTime.now());

        user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");
        user2.setEmail("bob@test.com");
        user2.setPasswordHash("hash2");
        user2.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetAllUsers() {
        when(userMapper.getAll()).thenReturn(Arrays.asList(user1, user2));

        var users = userService.getAll();
        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());

        verify(userMapper, times(1)).getAll();
    }

    @Test
    void testGetUserById() {
        when(userMapper.getById(1L)).thenReturn(Optional.of(user1));

        var user = userService.getById(1L);
        assertNotNull(user);
        assertEquals("Alice", user.getName());

        verify(userMapper, times(1)).getById(1L);
    }

    @Test
    void testCreateUser() {
        doNothing().when(userMapper).insert(user1);

        userService.create(user1);

        verify(userMapper, times(1)).insert(user1);
    }

    @Test
    void testDeleteUser() {
        userService.delete(user1.getId());

        verify(userMapper, times(1)).delete(user1.getId());
    }
}
