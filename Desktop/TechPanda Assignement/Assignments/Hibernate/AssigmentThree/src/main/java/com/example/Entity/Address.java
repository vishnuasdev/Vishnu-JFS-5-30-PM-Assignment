package com.example.Entity;
import jakarta.persistence.Embeddable;


public class Address {

    private int doorNo;
    private String street;
    private String city;
    private int pincode;

    public Address(){}

    public Address(int doorNo, String street, String city, int pincode) {
        this.doorNo = doorNo;
        this.street = street;
        this.city = city;
        this.pincode = pincode;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
