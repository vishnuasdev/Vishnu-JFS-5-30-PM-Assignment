package org.example.springassignment5.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("cloudRepo")
@Qualifier("cloudRepository")
public class CloudEmployeeRepository implements EmployeeRepository{

    @Override
    public String getRepository(){
        return "Cloud Storage";
    }
}
