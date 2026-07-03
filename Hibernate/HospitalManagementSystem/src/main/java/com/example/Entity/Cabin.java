package com.example.Entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "cabins")
public class Cabin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;

    @OneToOne(mappedBy = "cabin")
    private Doctor doctor;

    public void setRoomNumber(String rm) { this.roomNumber = rm; }
    public void setDoctor(Doctor doc) { this.doctor = doc; }
}