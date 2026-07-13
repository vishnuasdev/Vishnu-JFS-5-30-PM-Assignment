package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        EngineImplementation engine = context.getBean("engine",EngineImplementation.class);

        engine.startEngine();
    }
}
