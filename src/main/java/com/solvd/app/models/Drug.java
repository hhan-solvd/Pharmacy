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

    private Drug(Builder builder) {
        this.drugID = builder.drugID;
        this.name = builder.name;
        this.supplier = builder.supplier;
        this.manufacturer = builder.manufacturer;
        this.drugCategory = builder.drugCategory;
        this.price = builder.price;
    }

    public static class Builder {
        private int drugID;
        private String name;
        private Supplier supplier;
        private Manufacturer manufacturer;
        private DrugCategory drugCategory;
        private double price;

        public Builder withDrugID(int drugID) {
            this.drugID = drugID;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSupplier(Supplier supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder withManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withDrugCategory(DrugCategory drugCategory) {
            this.drugCategory = drugCategory;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Drug build() {
            return new Drug(this);
        }
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
