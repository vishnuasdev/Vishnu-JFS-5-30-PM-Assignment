package com.example.restassignmentone.controller;

import com.example.restassignmentone.model.Student;
import com.example.restassignmentone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService StudentServ;

    @GetMapping
    public List<Student> getAllStudents() {
        return StudentServ.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return StudentServ.getStudentById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = StudentServ.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
}