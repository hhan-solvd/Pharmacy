package com.solvd.app.models;

import java.util.Date;

public class Prescription {
    private int prescriptionID;
    private Date prescriptionDate;
    private Doctor doctor;
    private Customer customer;

    public Prescription() {
    }

    public Prescription(Date prescriptionDate, Doctor doctor, Customer customer) {
        this.prescriptionDate = prescriptionDate;
        this.doctor = doctor;
        this.customer = customer;
    }

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionID=" + prescriptionID +
                ", prescriptionDate=" + prescriptionDate +
                ", doctor=" + doctor +
                ", customer=" + customer +
                '}';
    }
}
