package com.example;

import com.example.Entity.*;
import com.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class MainApp {

    public void saveHospitalSetup(Doctor doctor, Cabin cabin, Patient patient, Medicine medicine) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            doctor.assignCabin(cabin);
            doctor.addPatient(patient);
            patient.getMedicines().add(medicine);

            session.persist(doctor);
            session.persist(medicine);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void verifyGetVsLoad(Long doctorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            System.out.println("Executing session.find()");
            Doctor docGet = session.find(Doctor.class, doctorId);
            if(docGet != null) System.out.println("Loaded Doctor Name: " + docGet.getName());

            System.out.println("--- Executing session.load() ---");
            Doctor docLoad = session.find(Doctor.class, doctorId);
            System.out.println("Proxy Assigned Identity ID: " + docLoad.getId());
            System.out.println("DB Hitted here to get name: " + docLoad.getName());
        }
    }

    public void verifyCachePerformance(Long doctorId) {
        try (Session session1 = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("--- Session 1: Loading Entity First Time (Hits DB) ---");
            session1.find(Doctor.class, doctorId);

            System.out.println("--- Session 1: Loading Entity Second Time (Hits L1 Cache - No duplicate SQL) ---");
            session1.find(Doctor.class, doctorId);
        }

        try (Session session2 = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("--- Session 2: Loading Entity across separate session context (Hits L2 Cache - No SQL) ---");
            session2.find(Doctor.class, doctorId);
        }
    }

    public void generateDoctorPatientLoadReport() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT d.name, COUNT(p.id) FROM Doctor d LEFT JOIN d.patients p GROUP BY d.name";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.getResultList();

            System.out.println("\nREPORT: Doctor Patient Assignment Metric Load");
            for (Object[] row : results) {
                System.out.println("Doctor: " + row[0] + " | Managed Case Patients: " + row[1]);
            }
        }
    }

    public void generatePatientMedicineMatrixReport() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p.name, m.name FROM Patient p JOIN p.medicines m WHERE m.price > :minPrice";
            Query<Object[]> query = session.createQuery(hql, Object[].class)
                    .setParameter("minPrice", 50.0);
            List<Object[]> results = query.getResultList();

            System.out.println("\nREPORT: Specialized Premium Prescription Auditing");
            for (Object[] row : results) {
                System.out.println("Patient Name: " + row[0] + " | Prescribed Costly Compound: " + row[1]);
            }
        }
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialization("Cardiology");

        Cabin cabin = new Cabin();
        cabin.setRoomNumber("A-101");

        Address patientAddress = new Address("123 Main St", "Chennai", "600001");
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setAddress(patientAddress);

        Medicine medicine = new Medicine();
        medicine.setName("Lipitor");
        medicine.setPrice(75.50);

        System.out.println("Step 1: Saving Hospital Setup");
        app.saveHospitalSetup(doctor, cabin, patient, medicine);

        Long sampleDoctorId = 1L;

        System.out.println("\nStep 2: Verifying Get vs Load behavior");
        app.verifyGetVsLoad(sampleDoctorId);

        System.out.println("\nStep 3: Verifying L1 & L2 Cache operations");
        app.verifyCachePerformance(sampleDoctorId);

        System.out.println("\nStep 4: Compiling HQL Reports");
        app.generateDoctorPatientLoadReport();
        app.generatePatientMedicineMatrixReport();

        System.out.println("\nFinal Step: Shutting down SessionFactory");
        HibernateUtil.getSessionFactory().close();
    }
}