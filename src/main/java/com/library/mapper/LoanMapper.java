package com.library.mapper;

import com.library.dto.LoanDto;
import com.library.entity.Loan;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    List<LoanDto> getAll();
    Optional<LoanDto> getById(Long id);
    void insert(Loan loan);
    void update(Loan loan);
    void delete(Long id);
}
