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

    private Specialty(Builder builder) {
        this.specialtyID = builder.specialtyID;
        this.name = builder.name;
    }

    public static class Builder {
        private int specialtyID;
        private String name;

        public Builder withSpecialtyID(int specialtyID) {
            this.specialtyID = specialtyID;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Specialty build() {
            return new Specialty(this);
        }
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
