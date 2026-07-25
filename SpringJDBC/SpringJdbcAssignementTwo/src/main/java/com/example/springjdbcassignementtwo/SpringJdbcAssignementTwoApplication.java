package com.example.springjdbcassignementtwo;

import com.example.springjdbcassignementtwo.model.Employee;
import com.example.springjdbcassignementtwo.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJdbcAssignementTwoApplication implements CommandLineRunner {

    private final EmployeeService service;

    public SpringJdbcAssignementTwoApplication(EmployeeService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcAssignementTwoApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("               EMPLOYEE PAYROLL MANAGEMENT SYSTEM                        ");
        System.out.println("-------------------------------------------------------------------------");

        // View All Initial Employees
        System.out.println("--- Initial Employee List ---");
        displayEmployees(service.getAllEmployees());

        // Add New Employee
        System.out.println("\n--- Adding New Employee ---");
        Employee newEmp = new Employee("Manoj Kumar", "IT", "Cybersecurity Analyst", 68000.00);
        service.addEmployee(newEmp);
        System.out.println(newEmp);
        System.out.println("Employee Added Successfully!");

        // Search Employee by ID
        System.out.println("\n--- Searching Employee by ID (ID: 3) ---");
        service.getEmployeeById(3).ifPresentOrElse(
                emp -> System.out.println("Found: " + emp),
                () -> System.out.println("Employee not found.")
        );

        // Update Employee Salary
        System.out.println("\n--- Updating Salary for Employee ID: 4 to ₹55,000.00 ---");
        if (service.updateEmployeeSalary(4, 55000.00)) {
            System.out.println("Salary Updated Successfully!");
        }

        // Delete Employee
        System.out.println("\n--- Deleting Employee ID: 6 ---");
        if (service.deleteEmployee(6)) {
            System.out.println("Employee ID 6 Deleted Successfully!");
        }

        // Updated Employee List
        System.out.println("\n--- Updated Employee List ---");
        displayEmployees(service.getAllEmployees());

        // Employees with Salary > ₹50,000
        System.out.println("\n--- High Earning Employees (Salary > ₹50,000) ---");
        displayEmployees(service.getHighEarningEmployees(50000.00));

        System.out.println("\n-------------------------------------------------------------------------");
    }

    private void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}