package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // for unique email
    private String uniqueEmail(String base) {
        return base + "+" + System.currentTimeMillis() + "@test.com";
    }

    @Test
    void testCreateAndGetUser() throws Exception {
        UserDto user = new UserDto(null, "Ivan", uniqueEmail("ivan"), LocalDateTime.now());

        // creation
        String response = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.email", containsString("ivan")))
                .andReturn().getResponse().getContentAsString();

        UserDto createdUser = objectMapper.readValue(response, UserDto.class);

        // getById
        mockMvc.perform(get("/api/users/{id}", createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUser.getId()))
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.email", containsString("ivan")));
    }

    @Test
    void testGetAllUsers() throws Exception {
        UserDto u1 = new UserDto(null, "Alice", uniqueEmail("alice"), LocalDateTime.now());
        UserDto u2 = new UserDto(null, "Bob", uniqueEmail("bob"), LocalDateTime.now());

        String resp1 = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u1)))
                .andReturn().getResponse().getContentAsString();
        String resp2 = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u2)))
                .andReturn().getResponse().getContentAsString();

        UserDto createdU1 = objectMapper.readValue(resp1, UserDto.class);
        UserDto createdU2 = objectMapper.readValue(resp2, UserDto.class);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[*].name", hasItems("Alice", "Bob")));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDto user = new UserDto(null, "OldName", uniqueEmail("old"), LocalDateTime.now());

        String resp = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andReturn().getResponse().getContentAsString();

        UserDto createdUser = objectMapper.readValue(resp, UserDto.class);


        createdUser.setName("NewName");
        createdUser.setEmail(uniqueEmail("new"));

        mockMvc.perform(put("/api/users/{id}", createdUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUser.getId()))
                .andExpect(jsonPath("$.name").value("NewName"))
                .andExpect(jsonPath("$.email", containsString("new")));
    }

    @Test
    void testDeleteUser() throws Exception {
        UserDto user = new UserDto(null, "DeleteMe", uniqueEmail("delete"), LocalDateTime.now());

        String resp = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andReturn().getResponse().getContentAsString();

        UserDto createdUser = objectMapper.readValue(resp, UserDto.class);

        // delete
        mockMvc.perform(delete("/api/users/{id}", createdUser.getId()))
                .andExpect(status().isOk());

        // check not found
        mockMvc.perform(get("/api/users/{id}", createdUser.getId()))
                .andExpect(status().isNotFound());
    }
}
