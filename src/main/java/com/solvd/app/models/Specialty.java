package com.solvd.app.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "specialty")
@XmlAccessorType(XmlAccessType.FIELD)
public class Specialty {

    @XmlAttribute(name = "specialty_id")
    private int specialtyID;

    @XmlElement(name = "name")
    private String name;

    public Specialty() {
    }

    public Specialty(String name) {
        this.name = name;
    }

    public int getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(int specialtyID) {
        this.specialtyID = specialtyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "specialtyID=" + specialtyID +
                ", name='" + name + '\'' +
                '}';
    }
}
