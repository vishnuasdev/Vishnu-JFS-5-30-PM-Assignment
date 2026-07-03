package com.example;

import com.example.Entity.Course;
import com.example.Entity.Student;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {

        Session ssn = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = ssn.beginTransaction();

            Course c1 = new Course(101, "Java");
            Course c2 = new Course(102, "Spring Boot");
            Course c3 = new Course(103, "Hibernate");
            Course c4 = new Course(104, "Microservices");

            ssn.persist(c1);
            ssn.persist(c2);
            ssn.persist(c3);
            ssn.persist(c4);

            Student std1 = new Student(1, "Vicky");
            Student std2 = new Student(2, "Vishwa");
            Student std3 = new Student(3, "Vishnu");

            std1.setCourses(Arrays.asList(c1, c2, c3));
            std2.setCourses(Arrays.asList(c2, c4));
            std3.setCourses(Arrays.asList(c1, c4));

            ssn.persist(std1);
            ssn.persist(std2);
            ssn.persist(std3);

            tx.commit();
            System.out.println("Data insert successfully");
            ssn.clear();

            System.out.println("\nLoading Student ID 1...");
            Student fetchStudent = ssn.find(Student.class, 1);
            System.out.println("Student: " + fetchStudent.getStudentName());
            System.out.println("\nEnrolled Courses:");
            for (Course c : fetchStudent.getCourses()) {
                System.out.println(c.getCourseName());
            }
            System.out.println("Data Loaded Successfully");

            System.out.println("\nFetching Again...");
            Student cachedStudent = ssn.find(Student.class, 1);
            System.out.println("Student Name: " + cachedStudent.getStudentName());
            System.out.println("Data Retrieved From Cache");
    }
}