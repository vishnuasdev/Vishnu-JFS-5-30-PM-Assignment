package com.example;

import java.util.List;
import com.example.Entity.Employee;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Hibernate Session Opened Successfully");

        String[] Name = {"Vishnu","Vishwa","Vicky","Mani","Vetri"};
        String[] Dept = {"IT","CSE","CSE","MED","SCE"};
        int[] Salary = {50000,40000,60000,32455,24554};

        for(int i =0;i<Name.length;i++) {
            Employee emp = new Employee(Name[i], Dept[i], Salary[i]);
            session.persist(emp);
        }

        tx.commit();

        List<Employee> employees = session.createQuery("from Employee",Employee.class).list();

        System.out.println("\nEmployee Saved:");
        for(Employee e: employees){
            System.out.println("ID: "+e.getEmployeeId()+
                    "\nName: "+e.getEmployeeName()+
                    "\nDept: "+e.getDepartment()+
                    "\nSalary: "+e.getSalary()+"\n");
        }

        System.out.println("Record Inserted Successfully");
        session.close();
    }
}
