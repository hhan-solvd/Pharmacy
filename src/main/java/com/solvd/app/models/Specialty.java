package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("specialty")
@XmlRootElement(name = "specialty")
@XmlAccessorType(XmlAccessType.FIELD)
public class Specialty {

    @JsonProperty("specialty_id")
    @XmlAttribute(name = "specialty_id")
    private int specialtyID;

    @JsonProperty("name")
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
