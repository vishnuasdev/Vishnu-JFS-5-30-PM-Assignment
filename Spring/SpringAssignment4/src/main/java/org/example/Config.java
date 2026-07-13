package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    public Bike bike(){
        return new Bike();
    }

    @Bean
    @Primary
    public Car car(){
        return new Car();
    }

    @Bean(name="engine")
    public EngineImplementation engine(){
        return  new EngineImplementation();
    }

}
