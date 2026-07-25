package com.example.springjdbcassignmentone;

import com.example.springjdbcassignmentone.model.Student;
import com.example.springjdbcassignmentone.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcAssignmentOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcAssignmentOneApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(StudentService service) {
        return args -> {
            System.out.println("\n================ ALL INITIAL STUDENTS ================");
            service.getAllStudents().forEach(System.out::println);

            System.out.println("\n================ SEARCH STUDENT BY ID (3) ================");
            Student student = service.getStudentById(3);
            System.out.println(student);

            System.out.println("\n================ UPDATE MARKS FOR ID 3 (New Marks: 91) ================");
            service.updateStudentMarks(3, 91);
            System.out.println(service.getStudentById(3));

            System.out.println("\n================ DELETE STUDENT ID 5 ================");
            service.deleteStudent(5);
            System.out.println("Student with ID 5 deleted successfully.");

            System.out.println("\n================ ADD NEW STUDENT ================");
            Student stud=new Student("Kavya Sundar", "kavya.s@gmail.com", "Cybersecurity", 94);
            service.addStudent(stud);
            System.out.println(stud);
            System.out.println("New Student added successfully");

            System.out.println("\n================ UPDATED ALL STUDENTS LIST ================");
            service.getAllStudents().forEach(System.out::println);
        };
    }
}