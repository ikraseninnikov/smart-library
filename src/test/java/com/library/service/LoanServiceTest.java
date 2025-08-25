package com.library.service;

import com.library.dto.LoanDto;
import com.library.entity.Loan;
import com.library.mapper.LoanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanService loanService;

    private Loan loan1;
    private LoanDto loanDto1;
    private LoanDto loanDto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        loan1 = new Loan();
        loan1.setId(1L);
        loan1.setBookId(100L);
        loan1.setUserId(10L);
        loan1.setLoanDate(LocalDate.now());
        loan1.setReturnDate(LocalDate.now().plusDays(7));

        loanDto1 = new LoanDto();
        loanDto1.setId(2L);
        loanDto1.setBookId(200L);
        loanDto1.setUserId(20L);
        loanDto1.setLoanDate(LocalDate.now());
        loanDto1.setReturnDate(LocalDate.now().plusDays(14));

        loanDto2 = new LoanDto();
        loanDto2.setId(2L);
        loanDto2.setBookId(300L);
        loanDto2.setUserId(30L);
        loanDto2.setLoanDate(LocalDate.now());
        loanDto2.setReturnDate(LocalDate.now().plusDays(14));
    }

    @Test
    void testGetAllLoans() {
        when(loanMapper.getAll()).thenReturn(Arrays.asList(loanDto1, loanDto2));

        var loans = loanService.getAll();
        assertEquals(2, loans.size());
        assertEquals(200L, loans.get(0).getBookId());

        verify(loanMapper, times(1)).getAll();
    }

    @Test
    void testGetLoanById() {
        when(loanMapper.getById(2L)).thenReturn(Optional.of(loanDto1));

        var loan = loanService.getById(2L);
        assertNotNull(loan);
        assertEquals(20L, loan.getUserId());

        verify(loanMapper, times(1)).getById(2L);
    }

    @Test
    void testCreateLoan() {
        doNothing().when(loanMapper).insert(loan1);

        loanService.create(loan1);

        verify(loanMapper, times(1)).insert(loan1);
    }

    @Test
    void testDeleteLoan() {
        loanService.delete(loan1.getId());

        verify(loanMapper, times(1)).delete(loan1.getId());
    }
}
