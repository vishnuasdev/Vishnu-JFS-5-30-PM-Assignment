package com.example.capstonechallenge.controller;

import com.example.capstonechallenge.model.Employee;
import com.example.capstonechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}