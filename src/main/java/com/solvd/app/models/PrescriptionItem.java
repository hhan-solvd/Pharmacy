package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("prescription_item")
@JsonPropertyOrder({"prescription_item_id", "drug", "quantity_prescribed"})
@XmlRootElement(name = "prescription_item")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionItem {

    @JsonProperty("prescription_item_id")
    @XmlAttribute(name = "prescription_item_id")
    private int prescriptionItemID;

    @JsonProperty("quantity_prescribed")
    @XmlElement(name = "quantity_prescribed")
    private int quantityPrescribed;

    @JsonIgnore
    @XmlTransient
    private Prescription prescription;

    @JsonProperty("drug")
    @XmlElement(name = "drug", type = Drug.class)
    private Drug drug;

    public PrescriptionItem() {
    }

    private PrescriptionItem(Builder builder) {
        this.prescriptionItemID = builder.prescriptionItemID;
        this.quantityPrescribed = builder.quantityPrescribed;
        this.prescription = builder.prescription;
        this.drug = builder.drug;
    }

    public static class Builder {
        private int prescriptionItemID;
        private int quantityPrescribed;
        private Prescription prescription;
        private Drug drug;

        public Builder withPrescriptionItemID(int prescriptionItemID) {
            this.prescriptionItemID = prescriptionItemID;
            return this;
        }

        public Builder withQuantityPrescribed(int quantityPrescribed) {
            this.quantityPrescribed = quantityPrescribed;
            return this;
        }

        public Builder withPrescription(Prescription prescription) {
            this.prescription = prescription;
            return this;
        }

        public Builder withDrug(Drug drug) {
            this.drug = drug;
            return this;
        }

        public PrescriptionItem build() {
            return new PrescriptionItem(this);
        }
    }

    public int getPrescriptionItemID() {
        return prescriptionItemID;
    }

    public void setPrescriptionItemID(int prescriptionItemID) {
        this.prescriptionItemID = prescriptionItemID;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public void setQuantityPrescribed(int quantityPrescribed) {
        this.quantityPrescribed = quantityPrescribed;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "prescriptionItemID=" + prescriptionItemID +
                ", quantityPrescribed=" + quantityPrescribed +
                ", prescription=" + prescription +
                ", drug=" + drug +
                '}';
    }
}
