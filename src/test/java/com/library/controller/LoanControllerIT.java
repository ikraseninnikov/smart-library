package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.BookDto;
import com.library.dto.LoanDto;
import com.library.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class LoanControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long userId;
    private Long bookId;
    private Long userId2;
    private Long bookId2;

    @BeforeEach
    void setupTestData() throws Exception {
        // user creation
        UserDto user = new UserDto(null, "Ivan", "ivan", "12345");
        String userResp = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        userId = objectMapper.readTree(userResp).get("id").asLong();

        UserDto user2 = new UserDto(null, "Maria", "maria", "12345");
        String userResp2 = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        userId2 = objectMapper.readTree(userResp2).get("id").asLong();

        // book creation
        BookDto book = new BookDto(null, "Test Title", "Test Author", true);
        String bookResp = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        bookId = objectMapper.readTree(bookResp).get("id").asLong();

        BookDto book2 = new BookDto(null, "Test Title", "Test Author", true);
        String bookResp2 = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book2)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        bookId2 = objectMapper.readTree(bookResp2).get("id").asLong();
    }

    @Test
    void testCreateAndGetLoan() throws Exception {
        LoanDto loan = new LoanDto();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(7));

        String response = mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn().getResponse().getContentAsString();

        LoanDto createdLoan = objectMapper.readValue(response, LoanDto.class);

        mockMvc.perform(get("/api/loans/{id}", createdLoan.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdLoan.getId()))
                .andExpect(jsonPath("$.bookId").value(bookId))
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void testGetAllLoans() throws Exception {
        LoanDto l1 = new LoanDto();
        l1.setBookId(bookId);
        l1.setUserId(userId);
        l1.setLoanDate(LocalDate.now());
        l1.setReturnDate(LocalDate.now().plusDays(3));

        LoanDto l2 = new LoanDto();
        l2.setBookId(bookId2);
        l2.setUserId(userId2);
        l2.setLoanDate(LocalDate.now());
        l2.setReturnDate(LocalDate.now().plusDays(5));

        String resp1 = mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(l1)))
                .andReturn().getResponse().getContentAsString();
        String resp2 = mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(l2)))
                .andReturn().getResponse().getContentAsString();

        LoanDto createdL1 = objectMapper.readValue(resp1, LoanDto.class);
        LoanDto createdL2 = objectMapper.readValue(resp2, LoanDto.class);


        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    void testUpdateLoan() throws Exception {
        LoanDto loan = new LoanDto();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(5));

        String resp = mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan)))
                .andReturn().getResponse().getContentAsString();

        LoanDto createdLoan = objectMapper.readValue(resp, LoanDto.class);

        createdLoan.setReturnDate(LocalDate.now().plusDays(10));

        mockMvc.perform(put("/api/loans/{id}", createdLoan.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdLoan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdLoan.getId()))
                .andExpect(jsonPath("$.returnDate").exists());
    }

    @Test
    void testDeleteLoan() throws Exception {
        LoanDto loan = new LoanDto();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(7));

        String resp = mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loan)))
                .andReturn().getResponse().getContentAsString();

        LoanDto createdLoan = objectMapper.readValue(resp, LoanDto.class);

        // delete
        mockMvc.perform(delete("/api/loans/{id}", createdLoan.getId()))
                .andExpect(status().isOk());

        // check not found
        mockMvc.perform(get("/api/loans/{id}", createdLoan.getId()))
                .andExpect(status().isNotFound());
    }
}
