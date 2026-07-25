package com.example.springjdbcassignementtwo.mapper;

import com.example.springjdbcassignementtwo.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setEmpName(rs.getString("emp_name"));
        employee.setDepartment(rs.getString("department"));
        employee.setDesignation(rs.getString("designation"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}