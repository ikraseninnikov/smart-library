--liquibase formatted sql

--changeset ivan:001_create_user
CREATE TABLE users (
    id NUMBER(19) PRIMARY KEY,
    username VARCHAR2(100) NOT NULL UNIQUE,
    email VARCHAR2(200) NOT NULL UNIQUE,
    password_hash VARCHAR2(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add comments to table columns
COMMENT ON COLUMN users.id IS 'Primary key, unique identifier for the user';
COMMENT ON COLUMN users.username IS 'Unique username chosen by the user';
COMMENT ON COLUMN users.email IS 'Unique email address of the user';
COMMENT ON COLUMN users.password_hash IS 'Hashed password of the user';
COMMENT ON COLUMN users.created_at IS 'Timestamp when the user record was created';

--changeset ivan:002_create_user_seq
CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
