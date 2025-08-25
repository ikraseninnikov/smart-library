package com.library.controller;

import com.library.dto.UserDto;
import com.library.entity.User;
import com.library.mapper.dto.UserMapperDTO;
import com.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(UserMapperDTO::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return UserMapperDTO.toDTO(user);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDTO) {
        User user = UserMapperDTO.toEntity(userDTO);
        userService.create(user);
        return UserMapperDTO.toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDTO) {
        User user = UserMapperDTO.toEntity(userDTO);
        user.setId(id);
        userService.update(user);
        return UserMapperDTO.toDTO(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
