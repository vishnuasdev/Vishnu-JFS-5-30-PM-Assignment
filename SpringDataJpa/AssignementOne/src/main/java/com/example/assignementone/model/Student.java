package com.example.assignementone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentjpa")
public class Student {

    @Id
    private int studentId;
    private String studentName;
    private String course;
    private int marks;

    public Student() {}

    public Student(int studentId, String studentName, String course, int marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
}