package com.solvd.app.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "prescription_item")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionItem {

    @XmlAttribute(name = "prescription_item_id")
    private int prescriptionItemID;

    @XmlElement(name = "quantity_prescribed")
    private int quantityPrescribed;

    @XmlElement(name = "prescription", type = Prescription.class)
    private Prescription prescription;

    @XmlElement(name = "drug", type = Drug.class)
    private Drug drug;

    public PrescriptionItem() {
    }

    public PrescriptionItem(int quantityPrescribed, Prescription prescription, Drug drug) {
        this.quantityPrescribed = quantityPrescribed;
        this.prescription = prescription;
        this.drug = drug;
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
