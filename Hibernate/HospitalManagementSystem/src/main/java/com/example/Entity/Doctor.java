package com.example.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cabin_id", referencedColumnName = "id")
    private Cabin cabin;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients = new ArrayList<>();

    public void assignCabin(Cabin cabin) {
        this.cabin = cabin;
        cabin.setDoctor(this);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.setDoctor(this);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String spec) { this.specialization = spec; }
}