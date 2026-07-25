package com.example.springjdbcassignementtwo.repository;

import com.example.springjdbcassignementtwo.mapper.EmployeeRowMapper;
import com.example.springjdbcassignementtwo.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add Employee
    public int save(Employee emp) {
        String sql = "INSERT INTO employees (emp_name, department, designation, salary) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, emp.getEmpName(), emp.getDepartment(), emp.getDesignation(), emp.getSalary());
    }

    // View All Employees
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    // Search Employee by ID
    public Optional<Employee> findById(int empId) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        List<Employee> list = jdbcTemplate.query(sql, new EmployeeRowMapper(), empId);
        return list.stream().findFirst();
    }

    // Update Employee Salary
    public int updateSalary(int empId, double newSalary) {
        String sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
        return jdbcTemplate.update(sql, newSalary, empId);
    }

    // Delete Employee
    public int deleteById(int empId) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        return jdbcTemplate.update(sql, empId);
    }

    // Additional Requirement: High Salary Employees (> ₹50,000)
    public List<Employee> findEmployeesWithSalaryGreaterThan(double threshold) {
        String sql = "SELECT * FROM employees WHERE salary > ?";
        return jdbcTemplate.query(sql, new EmployeeRowMapper(), threshold);
    }
}