package com.library.mapper.dto;

import com.library.dto.LoanDto;
import com.library.entity.Loan;

public class LoanMapperDTO {

    public static Loan toEntity(LoanDto dto) {
        if (dto == null) return null;
        Loan loan = new Loan();
        loan.setId(dto.getId());
        loan.setUserId(dto.getUserId());
        loan.setBookId(dto.getBookId());
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        return loan;
    }
}
