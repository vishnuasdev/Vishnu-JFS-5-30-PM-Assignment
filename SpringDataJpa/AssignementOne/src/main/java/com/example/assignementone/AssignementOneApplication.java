package com.example.assignementone;

import com.example.assignementone.model.Student;
import com.example.assignementone.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AssignementOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignementOneApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(StudentService studentService) {
        return args -> {
            List<Student> initialStudents = Arrays.asList(
                    new Student(101, "Arun", "Java", 85),
                    new Student(102, "Priya", "Python", 90),
                    new Student(103, "Karthik", "Spring Boot", 88),
                    new Student(104, "Suresh", "React", 80),
                    new Student(105, "Anitha", "Data Science", 92)
            );
            studentService.saveAllStudents(initialStudents);

            System.out.println("\n----- All Students -----");
            List<Student> students = studentService.getAllStudents();
            for (Student s : students) {
                System.out.println(s.getStudentId() + " - " + s.getStudentName() + " - " + s.getCourse() + " - " + s.getMarks());
            }

            System.out.println("\n----- Student By ID -----");
            Optional<Student> optionalStudent = studentService.getStudentById(101);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                System.out.println("ID : " + student.getStudentId());
                System.out.println("Name : " + student.getStudentName());
                System.out.println("Course : " + student.getCourse());
                System.out.println("Marks : " + student.getMarks());
            } else {
                System.out.println("Student not found!");
            }
        };
    }
}
