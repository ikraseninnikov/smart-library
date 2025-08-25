package com.library.controller;

import com.library.dto.LoanDto;
import com.library.entity.Loan;
import com.library.mapper.dto.LoanMapperDTO;
import com.library.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanDto> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public LoanDto getById(@PathVariable Long id) {
        return loanService.getById(id);
    }

    @PostMapping
    public LoanDto create(@RequestBody LoanDto loanDTO) {
        Loan loan = LoanMapperDTO.toEntity(loanDTO);
        loanService.create(loan);
        return loanService.getById(loan.getId());
    }

    @PutMapping("/{id}")
    public LoanDto update(@PathVariable Long id, @RequestBody LoanDto loanDTO) {
        Loan loan = LoanMapperDTO.toEntity(loanDTO);
        loan.setId(id);
        loanService.update(loan);
        return loanService.getById(loan.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}
