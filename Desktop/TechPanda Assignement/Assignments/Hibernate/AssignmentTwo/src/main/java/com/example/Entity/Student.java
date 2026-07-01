package com.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "college_students")
public class Student {

    @Id
    private int id;

    @Column(name = "student_name", nullable = false)
    private String name;

    private int mark;

    public Student() {}

    public Student(int id, String name, int mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMark() { return mark; }
    public void setMark(int mark) { this.mark = mark; }
}