package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("manufacturer")
@XmlRootElement(name = "manufacturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturer {

    @JsonProperty("manufacturer_id")
    @XmlAttribute(name = "manufacturer_id")
    private int manufacturerID;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    public Manufacturer() {
    }

    private Manufacturer(Builder builder) {
        this.manufacturerID = builder.manufacturerID;
        this.name = builder.name;
    }

    public static class Builder {
        private int manufacturerID;
        private String name;

        public Builder withManufacturerID(int manufacturerID) {
            this.manufacturerID = manufacturerID;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Manufacturer build() {
            return new Manufacturer(this);
        }
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerID=" + manufacturerID +
                ", name='" + name + '\'' +
                '}';
    }
}
