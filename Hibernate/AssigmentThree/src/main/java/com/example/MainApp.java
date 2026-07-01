package com.example;

import com.example.Entity.Address;
import com.example.Entity.Customer;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MainApp
{
    public static void main(String[] args )
    {
        Session ssn = HibernateUtil.getSessionFactory().openSession();

        //Insert Data
        Transaction tx = ssn.beginTransaction();
        Address add1 = new Address(23,"Main Road","Tindivanam",604001);
        Address add2 = new Address(45,"College Road","Chennai",600095);
        Address add3 = new Address(76,"Mettu Road","Villuppuram",604003);

        ssn.persist(new Customer(1,"Vishnu","7339334021",add1));
        ssn.persist(new Customer(2,"Vicky","7339334022",add1));
        ssn.persist(new Customer(3,"Mani","7339334023",add1));
        tx.commit();

        List<Customer> customers = ssn.createQuery("from Customer", Customer.class).list();

        for (Customer cust : customers) {
            System.out.println("\nCustomer ID : " + cust.getCustomerId());
            System.out.println("Customer Name : " + cust.getCustomerName());
            System.out.println("Mobile : " + cust.getMobileNo());

            Address addr = cust.getAddress();
            if (addr != null) {
                System.out.println("\nAddress:");
                System.out.println(addr.getDoorNo());
                System.out.println(addr.getStreet());
                System.out.println(addr.getCity());
                System.out.println(addr.getPincode());
            }
            System.out.println("---------------------------");
        }
    }
}
