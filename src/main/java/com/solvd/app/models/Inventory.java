package com.solvd.app.models;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "inventory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {

    @XmlAttribute(name = "inventory_id")
    private int inventoryID;

    @XmlElement(name = "quantity")
    private int quantity;

    @XmlElement(name = "drug", type = Drug.class)
    private Drug drug;

    @XmlElement(name = "pharmacy", type = Pharmacy.class)
    private Pharmacy pharmacy;

    public Inventory() {
    }

    public Inventory(int quantity, Drug drug, Pharmacy pharmacy) {
        this.quantity = quantity;
        this.drug = drug;
        this.pharmacy = pharmacy;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryID=" + inventoryID +
                ", quantity=" + quantity +
                ", drug=" + drug +
                ", pharmacy=" + pharmacy +
                '}';
    }
}
