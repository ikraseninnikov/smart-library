--liquibase formatted sql

--changeset ivan:003_create_loans
CREATE TABLE loans (
    id NUMBER(19) PRIMARY KEY,
    user_id NUMBER(19) NOT NULL,
    book_id NUMBER(19) NOT NULL,
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP,
    CONSTRAINT fk_loans_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_loans_book FOREIGN KEY (book_id) REFERENCES books(id)
);

--changeset ivan:003_loans_indexes
CREATE INDEX idx_loan_user_id ON loans(user_id);
CREATE INDEX idx_loan_book_id ON loans(book_id);

--changeset ivan:003_loans_comments
COMMENT ON TABLE loans IS 'Table for storing book loans';
COMMENT ON COLUMN loans.id IS 'Primary key';
COMMENT ON COLUMN loans.user_id IS 'Reference to the user who borrowed the book';
COMMENT ON COLUMN loans.book_id IS 'Reference to the borrowed book';
COMMENT ON COLUMN loans.loan_date IS 'Date when the book was loaned';
COMMENT ON COLUMN loans.return_date IS 'Date when the book was returned (nullable)';


--changeset ivan:003_loans_sequence
CREATE SEQUENCE loans_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
