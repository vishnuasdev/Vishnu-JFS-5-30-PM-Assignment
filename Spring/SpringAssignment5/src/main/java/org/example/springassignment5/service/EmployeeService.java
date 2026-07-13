package org.example.springassignment5.service;

import org.example.springassignment5.repository.CloudEmployeeRepository;
import org.example.springassignment5.repository.DbEmployeeRepository;

import org.example.springassignment5.model.Employee;

import org.example.springassignment5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Component
//@Scope("prototype")
@Scope("singleton")
public class EmployeeService {

    @Value("${employee.id}")
    private int empId;

    @Value("${employee.name}")
    private String empName;

    @Value("${employee.dept}")
    private String empDept;

    @Autowired
    @Qualifier("cloudRepository")
    private EmployeeRepository empRepo;

    public void getEmpDetails(){
        System.out.println("Employee ID: "+ empId+
                            "\nEmployee Name: "+ empName+
                            "\nDepartment: "+ empDept);

        System.out.println("Repository: "+empRepo.getRepository());
    }


}
