package com.example.restassignmenttwo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String empName;
    private String department;
    private Double salary;

    public Employee() {}

    public Employee(String empName, String department, Double salary) {
        this.empName = empName;
        this.department = department;
        this.salary = salary;
    }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}