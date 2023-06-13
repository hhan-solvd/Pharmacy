package com.solvd.app.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "manufacturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturer {

    @XmlAttribute(name = "manufacturer_id")
    private int manufacturerID;

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
