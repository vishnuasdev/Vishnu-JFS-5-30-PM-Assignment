package com.example.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_medicine",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private Set<Medicine> medicines = new HashSet<>();

    public void setName(String name) { this.name = name; }
    public void setAddress(Address addr) { this.address = addr; }
    public void setDoctor(Doctor doc) { this.doctor = doc; }
    public Set<Medicine> getMedicines() { return medicines; }
}