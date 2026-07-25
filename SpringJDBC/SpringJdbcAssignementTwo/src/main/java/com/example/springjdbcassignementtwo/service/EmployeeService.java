package com.example.springjdbcassignementtwo.service;

import com.example.springjdbcassignementtwo.model.Employee;
import com.example.springjdbcassignementtwo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(Employee emp) {
        repository.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(int empId) {
        return repository.findById(empId);
    }

    public boolean updateEmployeeSalary(int empId, double newSalary) {
        return repository.updateSalary(empId, newSalary) > 0;
    }

    public boolean deleteEmployee(int empId) {
        return repository.deleteById(empId) > 0;
    }

    public List<Employee> getHighEarningEmployees(double threshold) {
        return repository.findEmployeesWithSalaryGreaterThan(threshold);
    }
}