package org.example.springbootassignment2.repository;

import org.example.springbootassignment2.model.Employee;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository
public class EmployeeRepository {

    List<Employee> emp = new ArrayList<>();

    public void saveEmployee(Employee employee){
        emp.add(employee);
    }

    public List<Employee> getAllEmployee(){
        return emp;
    }

    public Employee searchById(int id){
        for(Employee em: emp){
            if(em.getEmpId() == id){
                return em;
            }
        }
        return null;
    }

//    public void deleteById(int id){
//        emp.removeIf(em -> em.getEmpId() == id);
//    }

    public boolean deleteEmployeeById(int id) {
        for (Employee em : emp) {
            if (em.getEmpId() == id) {
                emp.remove(em);
                return true;
            }
        }
        return false;
    }


}
