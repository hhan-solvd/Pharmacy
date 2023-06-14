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

    public Manufacturer(String name) {
        this.name = name;
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
