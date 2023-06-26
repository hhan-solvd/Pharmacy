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

    @JsonProperty("prescription_date")
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

    @JsonProperty("prescription_items")
    @XmlElementWrapper(name = "prescription_items")
    @XmlElement(name = "prescription_items", type = PrescriptionItem.class)
    private List<PrescriptionItem> prescriptionItems;

    public Prescription() {
    }

    private Prescription(Builder builder) {
        this.prescriptionID = builder.prescriptionID;
        this.prescriptionDate = builder.prescriptionDate;
        this.doctor = builder.doctor;
        this.customer = builder.customer;
        this.prescriptionItems = builder.prescriptionItems;
    }

    public static class Builder {
        private int prescriptionID;
        private Date prescriptionDate;
        private Doctor doctor;
        private Customer customer;
        private List<PrescriptionItem> prescriptionItems;

        public Builder withPrescriptionID(int prescriptionID) {
            this.prescriptionID = prescriptionID;
            return this;
        }

        public Builder withPrescriptionDate(Date prescriptionDate) {
            this.prescriptionDate = prescriptionDate;
            return this;
        }

        public Builder withDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder withPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
            this.prescriptionItems = prescriptionItems;
            return this;
        }

        public Prescription build() {
            return new Prescription(this);
        }
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
