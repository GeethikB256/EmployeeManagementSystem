CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           first_name VARCHAR(35) NOT NULL,
                           last_name VARCHAR(20) NOT NULL,
                           email VARCHAR(50) NOT NULL UNIQUE,
                           department VARCHAR(45) NOT NULL,
                           job_title VARCHAR(30) NOT NULL,
                           salary DOUBLE PRECISION NOT NULL
);