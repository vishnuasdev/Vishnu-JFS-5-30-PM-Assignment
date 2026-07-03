package com.example.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    private int id;
    private String studentName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course_tab",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<Course> courses = new ArrayList<>();

    public Student() {}

    public Student(int id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}