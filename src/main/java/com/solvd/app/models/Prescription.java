package com.solvd.app.models;

import com.solvd.app.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prescription {

    @XmlAttribute(name = "prescription_id")
    private int prescriptionID;

    @XmlElement(name = "prescription_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date prescriptionDate;

    @XmlElement(name = "doctor", type = Doctor.class)
    private Doctor doctor;

    @XmlElement(name = "customer", type = Customer.class)
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
