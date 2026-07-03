package com.example.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "medicines")
    private Set<Patient> patients = new HashSet<>();

    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }
}