package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Account ac = context.getBean("account", Account.class);
        System.out.println("Account Number :"+ ac.getAccountNumber()+
                        "\nCustomer Name :" + ac.getCustomerName()+
                        "\nBalance: " + ac.getBalance());
    }
}
