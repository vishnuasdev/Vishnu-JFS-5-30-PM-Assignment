package org.example.springbootassignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.example.springbootassignment2.repository.EmployeeRepository;
import org.example.springbootassignment2.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public void addEmployee(Employee employee){
        empRepo.saveEmployee(employee);
    }

    public List<Employee> fetchAllEmployee(){
        return empRepo.getAllEmployee();
    }

    public Employee searchById(int id){
        return empRepo.searchById(id);
    }

    public boolean deleteById(int id){
        return empRepo.deleteEmployeeById(id);
    }
}
