package com.example.springjdbcassignmentone.service;

import com.example.springjdbcassignmentone.model.Student;
import com.example.springjdbcassignmentone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudentMarks(int id, int marks) {
        studentRepository.updateMarks(id, marks);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}