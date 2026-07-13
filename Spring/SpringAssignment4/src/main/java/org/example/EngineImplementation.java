package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class EngineImplementation {

    @Autowired
    private Vehicle vehicle;

    public void startEngine(){
        System.out.println("Vehicle Type: "+vehicle.useVehicle());
        System.out.println("Engine Started");
    }
}
