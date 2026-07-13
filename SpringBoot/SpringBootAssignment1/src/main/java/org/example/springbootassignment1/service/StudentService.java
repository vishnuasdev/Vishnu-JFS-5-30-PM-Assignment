package org.example.springbootassignment1.service;

import org.springframework.stereotype.Service;
import org.example.springbootassignment1.repository.StudentRepository;
import org.example.springbootassignment1.model.Student;
import java.util.List;

@Service
public class StudentService {

    StudentRepository studRepo;

    public StudentService(StudentRepository studRepo) {
        this.studRepo = studRepo;
    }

    public void addStudent(Student student){
        studRepo.saveStudent(student);
    }

    public List<Student> getAllStudent(){
        return studRepo.getStudent();
    }

    public Student getById(int id){
        return studRepo.getByStudId(id);
    }
}
