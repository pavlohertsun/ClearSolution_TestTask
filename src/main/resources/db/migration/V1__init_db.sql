CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    email VARCHAR(250) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(250),
    phone_number VARCHAR(20),
    CONSTRAINT check_email CHECK (email LIKE '%@%.%' AND Email NOT LIKE '%@%@%'),
    CONSTRAINT check_birth_date CHECK (birth_date < CURRENT_DATE)
);

CREATE SEQUENCE user_seq START 1;

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('user_seq');