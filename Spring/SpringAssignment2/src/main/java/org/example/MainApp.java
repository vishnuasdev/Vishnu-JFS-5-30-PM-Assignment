package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Product prod = context.getBean("product", Product.class);

        System.out.println("Product Id: " + prod.getProductId() +
                "\nProduct Name: " + prod.getProductName() +
                "\nPrice: " + prod.getPrice());
    }
}
