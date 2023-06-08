package com.solvd.app.models;

public class PrescriptionItem {
    private int prescriptionItemID;
    private int quantityPrescribed;
    private Prescription prescription;
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
