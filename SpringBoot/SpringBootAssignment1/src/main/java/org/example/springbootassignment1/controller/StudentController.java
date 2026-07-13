package org.example.springbootassignment1.controller;

import org.springframework.web.bind.annotation.*;
import org.example.springbootassignment1.service.StudentService;
import org.example.springbootassignment1.model.Student;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    StudentService studServ;

    public StudentController(StudentService studServ) {
        this.studServ = studServ;
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        studServ.addStudent(student);
        return "Student Insert Successfully";
    }

    // Url path: /?id=102
    @GetMapping("/")
    public Student getById(@RequestParam int id){
        return studServ.getById(id);
    }

    // Url path: /102
    @GetMapping("/{id}")
    public Student getById2(@PathVariable int id) {
        return studServ.getById(id);
    }

    @GetMapping("/get")
    public List<Student> fetctAll(){
        return studServ.getAllStudent();
    }

}
