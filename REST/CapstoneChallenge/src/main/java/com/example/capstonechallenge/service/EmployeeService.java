package com.example.capstonechallenge.service;

import com.example.capstonechallenge.model.Employee;
import com.example.capstonechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedData) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setName(updatedData.getName());
            emp.setDepartment(updatedData.getDepartment());
            emp.setSalary(updatedData.getSalary());
            return employeeRepository.save(emp);
        }).orElse(null);
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}