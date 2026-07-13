package org.example.springassignment5.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class DbEmployeeRepository implements EmployeeRepository{

    @Override
    public String getRepository(){
        return "Database Storage";
    }
}
