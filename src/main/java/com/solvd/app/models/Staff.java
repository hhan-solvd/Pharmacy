package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("staff")
@XmlRootElement(name = "staff")
@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

    @JsonProperty("staff_id")
    @XmlAttribute(name = "staff_id")
    private int staffID;

    @JsonProperty("person")
    @XmlElement(name = "person", type = Person.class)
    private Person person;

    @JsonIgnore
    @XmlTransient
    private Pharmacy pharmacy;

    @JsonProperty("position")
    @XmlElement(name = "position", type = Position.class)
    private Position position;

    public Staff() {
    }

    public Staff(Person person, Pharmacy pharmacy, Position position) {
        this.person = person;
        this.pharmacy = pharmacy;
        this.position = position;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", person=" + person +
                ", pharmacy=" + pharmacy +
                ", position=" + position +
                '}';
    }
}
