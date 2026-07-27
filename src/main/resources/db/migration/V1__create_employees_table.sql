CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           firstname VARCHAR(35) NOT NULL,
                           lastname VARCHAR(20) NOT NULL,
                           email VARCHAR(50) NOT NULL UNIQUE,
                           department VARCHAR(45) NOT NULL,
                           jobTitle VARCHAR(30) NOT NULL,
                           salary DOUBLE PRECISION NOT NULL
);