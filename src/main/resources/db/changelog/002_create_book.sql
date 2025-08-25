--liquibase formatted sql

--changeset ivan:002_create_books
CREATE TABLE books (
    id NUMBER(19) PRIMARY KEY,
    title VARCHAR2(200) NOT NULL,
    author VARCHAR2(150) NOT NULL,
    is_available NUMBER(1) DEFAULT 1 NOT NULL
);

--changeset ivan:002_books_comments
COMMENT ON TABLE books IS 'Table for storing books';
COMMENT ON COLUMN books.id IS 'Primary key';
COMMENT ON COLUMN books.title IS 'Book title';
COMMENT ON COLUMN books.author IS 'Book author';
COMMENT ON COLUMN books.is_available IS 'Availability flag: 1 - available, 0 - not available';


--changeset ivan:002_books_sequence
CREATE SEQUENCE books_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

