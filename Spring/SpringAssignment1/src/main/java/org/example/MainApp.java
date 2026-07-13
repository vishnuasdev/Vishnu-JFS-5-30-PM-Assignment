package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp
{
    public static void main( String[] args )
    {
        FoodDeliveryAgent fdp = SpringConfig().getBean("foodDeliveryAgent", FoodDeliveryAgent.class);

        fdp.display();

    }

    public static ApplicationContext SpringConfig(){
        return new ClassPathXmlApplicationContext("spring.xml");

    }
}
