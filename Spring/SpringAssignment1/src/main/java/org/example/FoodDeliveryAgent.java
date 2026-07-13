package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class FoodDeliveryAgent{

    private Delivery delivery;

    public void setFoodDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    public void display(){
        delivery.deliveryPatner();
    }
}
