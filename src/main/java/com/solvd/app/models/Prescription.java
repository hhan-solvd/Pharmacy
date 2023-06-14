package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.app.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@JsonRootName("prescription")
@XmlRootElement(name = "prescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prescription {

    @JsonProperty("prescription_id")
    @XmlAttribute(name = "prescription_id")
    private int prescriptionID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @XmlElement(name = "prescription_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date prescriptionDate;

    @JsonProperty("doctor")
    @XmlElement(name = "doctor", type = Doctor.class)
    private Doctor doctor;

    @JsonProperty("customer")
    @XmlElement(name = "customer", type = Customer.class)
    private Customer customer;

    @JsonProperty("prescriptionItems")
    @XmlElementWrapper(name = "prescription_items")
    @XmlElement(name = "prescription_items", type = PrescriptionItem.class)
    private List<PrescriptionItem> prescriptionItems;

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

    public List<PrescriptionItem> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionID=" + prescriptionID +
                ", prescriptionDate=" + prescriptionDate +
                ", doctor=" + doctor +
                ", customer=" + customer +
                ", prescriptionItems=" + prescriptionItems +
                '}';
    }
}
