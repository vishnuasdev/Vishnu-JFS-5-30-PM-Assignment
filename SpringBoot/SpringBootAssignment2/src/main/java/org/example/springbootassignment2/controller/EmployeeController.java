package org.example.springbootassignment2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import  org.example.springbootassignment2.service.EmployeeService;
import  org.example.springbootassignment2.model.Employee;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee){
        empService.addEmployee(employee);
        return "Employee successfully inserted";
    }

    @GetMapping("/getall")
    public List<Employee> fetchAllEmployees(){
        return empService.fetchAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee searchById(@PathVariable int id){
        return empService.searchById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){

        return empService.deleteById(id)?"Employee Delete successfully":"Employee not Fount!";
    }




}
