package org.example.springassignment5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.springassignment5.service.EmployeeService;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringAssignment5Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringAssignment5Application.class, args);

        EmployeeService empServ= context.getBean("employeeService", EmployeeService.class);

        EmployeeService empServ1= context.getBean("employeeService", EmployeeService.class);

        empServ.getEmpDetails();

        System.out.println("Sigleton :"+ (empServ == empServ1));
        System.out.println("Prototype :"+ (empServ != empServ1));
    }
}
