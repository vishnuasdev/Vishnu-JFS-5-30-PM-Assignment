package com.example.assignmenttwo;

import com.example.assignmenttwo.model.Employee;
import com.example.assignmenttwo.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AssignmentTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentTwoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(EmployeeService employeeService) {
        return args -> {
            List<Employee> initialEmployees = Arrays.asList(
                    new Employee("John", "IT", 50000),
                    new Employee("David", "HR", 45000),
                    new Employee("Rahul", "Finance", 60000)
            );
            employeeService.saveAllEmployees(initialEmployees);

            System.out.println("\nEmployee Details");
            List<Employee> employees = employeeService.getAllEmployees();
            for (Employee e : employees) {
                System.out.println(e.getEmpId() + " - " + e.getEmpName() + " - " + e.getDepartment() + " - " + (int)e.getSalary());
            }

            System.out.println("\n----- Employee By ID -----");
            Optional<Employee> optionalEmployee = employeeService.getEmployeeById(1L);
            if (optionalEmployee.isPresent()) {
                Employee e = optionalEmployee.get();
                System.out.println("ID : " + e.getEmpId());
                System.out.println("Name : " + e.getEmpName());
                System.out.println("Department : " + e.getDepartment());
                System.out.println("Salary : " + (int)e.getSalary());
            } else {
                System.out.println("Employee not found!");
            }
        };
    }
}
