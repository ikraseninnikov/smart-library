package com.library.mapper.dto;

import com.library.dto.UserDto;
import com.library.entity.User;
import com.library.util.PasswordUtils;

public class UserMapperDTO {

    public static UserDto toDTO(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        String passwordHash = PasswordUtils.hashPassword(dto.getPassword());
        user.setPasswordHash(passwordHash);
        return user;
    }
}

