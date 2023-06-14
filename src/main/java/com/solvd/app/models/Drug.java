package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("drug")
@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug {

    @JsonProperty("drug_id")
    @XmlAttribute(name = "drug_id")
    private int drugID;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("supplier")
    @XmlElement(name = "supplier", type = Supplier.class)
    private Supplier supplier;

    @JsonProperty("manufacturer")
    @XmlElement(name = "manufacturer", type = Manufacturer.class)
    private Manufacturer manufacturer;

    @JsonProperty("drug_category")
    @XmlElement(name = "drug_category", type = DrugCategory.class)
    private DrugCategory drugCategory;

    @JsonProperty("price")
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
