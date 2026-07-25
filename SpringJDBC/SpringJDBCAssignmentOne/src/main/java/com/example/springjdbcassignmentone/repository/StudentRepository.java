package com.example.springjdbcassignmentone.repository;

import com.example.springjdbcassignmentone.mapper.StudentRowMapper;
import com.example.springjdbcassignmentone.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Student student) {
        String sql = "INSERT INTO students (student_name, email, course, marks) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getStudentName(), student.getEmail(), student.getCourse(), student.getMarks());
    }

    public int updateMarks(int id, int marks) {
        String sql = "UPDATE students SET marks = ? WHERE student_id = ?";
        return jdbcTemplate.update(sql, marks, id);
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
}