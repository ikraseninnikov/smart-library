package com.library.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate  getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate  loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate  getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate  returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                bookId == loan.bookId &&
                userId == loan.userId &&
                Objects.equals(loanDate, loan.loanDate) &&
                Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, userId, loanDate, returnDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
