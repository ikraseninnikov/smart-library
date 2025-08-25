--liquibase formatted sql

--changeset ivan:004_insert_test_data
DECLARE
  TYPE t_ids IS TABLE OF NUMBER(19);
  user_ids t_ids := t_ids();
  book_ids t_ids := t_ids();
  v_user_id NUMBER(19);
  v_book_id NUMBER(19);
  idx_user PLS_INTEGER;
  idx_book PLS_INTEGER;
BEGIN
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'ivan', 'ivan@mail.com', 'hash1', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'anna', 'anna@mail.com', 'hash2', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'peter', 'peter@mail.com', 'hash3', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'olga', 'olga@mail.com', 'hash4', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'sergey', 'sergey@mail.com', 'hash5', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'daria', 'daria@mail.com', 'hash6', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'alex', 'alex@mail.com', 'hash7', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'maria', 'maria@mail.com', 'hash8', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'nikolay', 'nikolay@mail.com', 'hash9', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;
    INSERT INTO users (id, username, email, password_hash, created_at)
    VALUES (users_seq.NEXTVAL, 'elena', 'elena@mail.com', 'hash10', CURRENT_TIMESTAMP)
    RETURNING id INTO v_user_id;
    user_ids.EXTEND;
    user_ids(user_ids.COUNT) := v_user_id;

    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Clean Code', 'Robert Martin', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Effective Java', 'Joshua Bloch', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Design Patterns', 'Erich Gamma', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Refactoring', 'Martin Fowler', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Domain-Driven Design', 'Eric Evans', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Spring in Action', 'Craig Walls', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Java Concurrency in Practice', 'Brian Goetz', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Head First Java', 'Kathy Sierra', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'The Pragmatic Programmer', 'Andrew Hunt', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;
    INSERT INTO books (id, title, author, is_available)
    VALUES (books_seq.NEXTVAL, 'Microservices Patterns', 'Chris Richardson', 1)
    RETURNING id INTO v_book_id;
    book_ids.EXTEND;
    book_ids(book_ids.COUNT) := v_book_id;

    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-01','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-03','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-07-28','YYYY-MM-DD'), TO_DATE('2025-08-15','YYYY-MM-DD'));
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-05','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-07-20','YYYY-MM-DD'), TO_DATE('2025-08-10','YYYY-MM-DD'));
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-02','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-04','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-07-22','YYYY-MM-DD'), TO_DATE('2025-08-11','YYYY-MM-DD'));
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL, v_user_id, v_book_id, TO_DATE('2025-08-06','YYYY-MM-DD'), NULL);
    idx_user := FLOOR(DBMS_RANDOM.VALUE(1, user_ids.COUNT + 1));
    idx_book := FLOOR(DBMS_RANDOM.VALUE(1, book_ids.COUNT + 1));
    v_user_id := user_ids(idx_user);
    v_book_id := book_ids(idx_book);
    INSERT INTO loans (id, user_id, book_id, loan_date, return_date)
    VALUES (loans_seq.NEXTVAL,v_user_id, v_book_id, TO_DATE('2025-08-07','YYYY-MM-DD'), NULL);
END;
/