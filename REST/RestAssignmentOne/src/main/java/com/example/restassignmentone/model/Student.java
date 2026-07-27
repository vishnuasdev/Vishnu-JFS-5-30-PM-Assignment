package com.example.restassignmentone.model;


import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String course;
    private Integer marks;

    public Student() {}

    public Student(String name, String course, Integer marks) {
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public Integer getMarks() { return marks; }
    public void setMarks(Integer marks) { this.marks = marks; }
}