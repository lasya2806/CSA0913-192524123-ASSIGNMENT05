CREATE DATABASE assignment5;

USE assignment5;

CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    pwd VARCHAR(100)
);

INSERT INTO users VALUES
('admin', 'correct123');

SELECT * FROM users;
