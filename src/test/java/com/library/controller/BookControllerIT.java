package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetBook() throws Exception {
        BookDto book = new BookDto(null, "Test Title", "Test Author", true);

        String response = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn().getResponse().getContentAsString();

        BookDto createdBook = objectMapper.readValue(response, BookDto.class);


        mockMvc.perform(get("/api/books/{id}", createdBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdBook.getId()))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        BookDto b1 = new BookDto(null, "Book1", "Author1", true);
        BookDto b2 = new BookDto(null, "Book2", "Author2", true);

        String resp1 = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(b1)))
                .andReturn().getResponse().getContentAsString();
        String resp2 = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(b2)))
                .andReturn().getResponse().getContentAsString();

        BookDto createdB1 = objectMapper.readValue(resp1, BookDto.class);
        BookDto createdB2 = objectMapper.readValue(resp2, BookDto.class);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[*].title", hasItems("Book1", "Book2")));
    }

    @Test
    void testUpdateBook() throws Exception {
        BookDto book = new BookDto(null, "Old Title", "Old Author", true);

        String resp = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andReturn().getResponse().getContentAsString();

        BookDto createdBook = objectMapper.readValue(resp, BookDto.class);


        createdBook.setTitle("New Title");
        createdBook.setAuthor("New Author");

        mockMvc.perform(put("/api/books/{id}", createdBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdBook.getId()))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"));
    }

    @Test
    void testDeleteBook() throws Exception {
        BookDto book = new BookDto(null, "ToDelete", "Author", true);

        String resp = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andReturn().getResponse().getContentAsString();

        BookDto createdBook = objectMapper.readValue(resp, BookDto.class);

        // delete
        mockMvc.perform(delete("/api/books/{id}", createdBook.getId()))
                .andExpect(status().isOk());

        // check not found
        mockMvc.perform(get("/api/books/{id}", createdBook.getId()))
                .andExpect(status().isNotFound());
    }
}
