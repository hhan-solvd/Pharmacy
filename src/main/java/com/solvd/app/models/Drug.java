package com.solvd.app.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug {

    @XmlAttribute(name = "drug_id")
    private int drugID;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "supplier", type = Supplier.class)
    private Supplier supplier;

    @XmlElement(name = "manufacturer", type = Manufacturer.class)
    private Manufacturer manufacturer;

    @XmlElement(name = "drug_category", type = DrugCategory.class)
    private DrugCategory drugCategory;

    @XmlElement(name = "price")
    private double price;

    public Drug() {
    }

    public Drug(String name, Supplier supplier, Manufacturer manufacturer, DrugCategory drugCategory, double price) {
        this.name = name;
        this.supplier = supplier;
        this.manufacturer = manufacturer;
        this.drugCategory = drugCategory;
        this.price = price;
    }

    public int getDrugID() {
        return drugID;
    }

    public void setDrugID(int drugID) {
        this.drugID = drugID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public DrugCategory getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(DrugCategory drugCategory) {
        this.drugCategory = drugCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugID=" + drugID +
                ", name='" + name + '\'' +
                ", supplier=" + supplier +
                ", manufacturer=" + manufacturer +
                ", drugCategory=" + drugCategory +
                ", price=" + price +
                '}';
    }
}
