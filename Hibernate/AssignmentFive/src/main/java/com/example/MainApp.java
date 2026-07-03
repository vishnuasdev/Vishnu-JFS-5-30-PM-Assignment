package com.example;

import com.example.Entity.Product;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        Session ssn = HibernateUtil.getSessionFactory().openSession();


        Transaction tx = ssn.beginTransaction();

        ssn.persist(new Product(101, "Laptop", "Electronics", 55000, 10));
        ssn.persist(new Product(102, "Mobile", "Electronics", 25000, 15));
        ssn.persist(new Product(103, "Headphone", "Electronics", 1500, 40));
        ssn.persist(new Product(104, "T-Shirt", "Apparel", 800, 50));
        ssn.persist(new Product(105, "Jeans", "Apparel", 2200, 25));
        ssn.persist(new Product(106, "Blender", "Home", 3500, 8));
        ssn.persist(new Product(107, "Coffee Maker", "Home", 4500, 5));
        ssn.persist(new Product(108, "Novel Book", "Books", 450, 100));
        ssn.persist(new Product(109, "Smart Watch", "Electronics", 12000, 20));
        ssn.persist(new Product(110, "Running Shoes", "Apparel", 5000, 12));
        tx.commit();


        System.out.println("\n--- Fetch All Products ---");
        List<Product> all = ssn.createQuery("from Product", Product.class).list();
        all.forEach(p -> System.out.println(p.getProductName()));

        System.out.println("\nFetch products by category:");
        List<Product> electronics = ssn.createQuery("from Product where category = :cat", Product.class)
                .setParameter("cat", "Electronics").list();
        electronics.forEach(p -> System.out.println(p.getProductId() + " - " + p.getProductName() + " - " + p.getPrice()));

        System.out.println("\n--- Fetch products with price > 1000 ---");
        List<Product> expensive = ssn.createQuery("from Product where price > 1000", Product.class).list();
        expensive.forEach(p -> System.out.println(p.getProductName() + " ($" + p.getPrice() + ")"));

        System.out.println("\n--- Fetch only product names and prices ---");
        List<Object[]> projections = ssn.createQuery("select p.productName, p.price from Product p", Object[].class).list();
        projections.forEach(row -> System.out.println("Name: " + row[0] + " | Price: " + row[1]));

        System.out.println("\nUsing GET:");
        Product pGet = ssn.find(Product.class, 101);
        if (pGet != null) System.out.println("Product Found: " + pGet.getProductName());

        System.out.println("\nUsing LOAD");
        Product pLoad = ssn.getReference(Product.class, 102);
        System.out.println("Proxy Object Created");
        System.out.println("Accessing Proxy Data > Name: " + pLoad.getProductName());


        System.out.println("\n--- Testing Ehcache ---");

        Session ssn1 = HibernateUtil.getSessionFactory().openSession();
        System.out.println("First Retrieval (Session 1):");
        Product p1 = ssn1.find(Product.class, 101);
        System.out.println("Loaded: " + p1.getProductName());

        Session ssn2 = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\nSecond Retrieval (Session 2):");
        Product p2 = ssn2.find(Product.class, 101);
        System.out.println("Loaded: " + p2.getProductName());
        System.out.println("Data Loaded From Ehcache");
    }
}