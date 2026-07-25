USE payrolldb;

CREATE TABLE IF NOT EXISTS employees (
     emp_id INT PRIMARY KEY AUTO_INCREMENT,
     emp_name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    designation VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
    );