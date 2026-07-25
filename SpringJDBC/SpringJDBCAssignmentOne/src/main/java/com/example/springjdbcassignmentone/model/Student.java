package com.example.springjdbcassignmentone.model;

public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String course;
    private int marks;

    public Student() {}

    public Student(String studentName, String email, String course, int marks) {
        this.studentName = studentName;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public Student(int studentId, String studentName, String email, String course, int marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Name: %-15s | Email: %-22s | Course: %-22s | Marks: %d",
                studentId, studentName, email, course, marks);
    }
}