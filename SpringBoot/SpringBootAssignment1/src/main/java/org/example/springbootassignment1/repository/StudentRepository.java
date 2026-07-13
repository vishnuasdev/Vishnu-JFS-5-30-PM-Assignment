package org.example.springbootassignment1.repository;


import org.springframework.stereotype.Repository;
import org.example.springbootassignment1.model.Student;
import java.util.List;
import java.util.ArrayList;

@Repository
public class StudentRepository {

    private List<Student> stud = new ArrayList<>();

    public void saveStudent(Student student){
        stud.add(student);
    }

    public List<Student> getStudent(){
        return stud;
    }

    public Student getByStudId(int id){
        for(Student std:stud){
            if(std.getId() == id){
                return std;
            }
        }
        return null;
    }
}
