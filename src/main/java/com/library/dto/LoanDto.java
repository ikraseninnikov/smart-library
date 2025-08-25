package com.library.dto;

import java.time.LocalDate;

public class LoanDto {
    private Long id;
    private Long userId;
    private Long bookId;
    private String userName;
    private String userEmail;
    private String bookTitle;
    private String bookAuthor;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanDto() {}

    public LoanDto(Long id, Long userId, Long bookId, String userName, String userEmail, String bookTitle, String bookAuthor, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
