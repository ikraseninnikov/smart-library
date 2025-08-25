package com.library.service;

import com.library.dto.LoanDto;
import com.library.entity.Loan;
import com.library.exception.NotFoundException;
import com.library.mapper.LoanMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanService {
    private final LoanMapper loanMapper;

    public LoanService(LoanMapper loanMapper) { this.loanMapper = loanMapper; }

    public List<LoanDto> getAll() { return loanMapper.getAll(); }

    public LoanDto getById(Long id) {
        return loanMapper.getById(id)
                .orElseThrow(() -> new NotFoundException("Loan with id " + id + " not found"));
    }
    public void create(Loan loan) { loanMapper.insert(loan); }
    public void update(Loan loan) { loanMapper.update(loan); }
    public void delete(Long id) { loanMapper.delete(id); }
}
