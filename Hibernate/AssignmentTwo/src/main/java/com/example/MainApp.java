package com.example;

import com.example.Entity.Student;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        try (Session ssn = HibernateUtil.getSessionFactory().openSession()) {

            // Insert 3 Students
            Transaction txInsert = ssn.beginTransaction();
            ssn.persist(new Student(1, "Vishnu", 85));
            ssn.persist(new Student(2, "Vishwa", 90));
            ssn.persist(new Student(3, "Mani", 78));
            txInsert.commit();

            //Fetch all students
            System.out.println("\n----- Student List -----");
            displayAllStudents(ssn);

            //fetch by student id
            System.out.println("\nFetching Student ID 1...");
            Student singleStudent = ssn.find(Student.class, 1);
            if (singleStudent != null) {
                System.out.println(singleStudent.getId() + " - " + singleStudent.getName() + " - " + singleStudent.getMark());
            }

            //Update Marks
            System.out.println("\nUpdating Marks...");
            Transaction txUpdate = ssn.beginTransaction();
            Student studentToUpdate = ssn.find(Student.class, 2);
            if (studentToUpdate != null) {
                studentToUpdate.setMark(95); // Update mark to 95
                txUpdate.commit();
                System.out.println(studentToUpdate.getId() + " - " + studentToUpdate.getName() + " - " + studentToUpdate.getMark());
            } else {
                txUpdate.rollback();
            }



            //Delete Student
            System.out.println("\nDeleting Student ID 3");
            Transaction txDelete = ssn.beginTransaction();
            Student studentToDelete = ssn.find(Student.class, 3);
            if (studentToDelete != null) {
                ssn.remove(studentToDelete);
                txDelete.commit();
            } else {
                txDelete.rollback();
            }

            //Remaining Records:
            System.out.println("\nRemaining Records:");
            displayAllStudents(ssn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayAllStudents(Session ssn) {
        List<Student> students = ssn.createQuery("from Student", Student.class).list();
        for (Student std : students) {
            System.out.println(std.getId() + " - " + std.getName() + " - " + std.getMark());
        }
    }
}