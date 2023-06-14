package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("doctor")
@XmlRootElement(name = "doctor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor {

    @JsonProperty("doctor_id")
    @XmlAttribute(name = "doctor_id")
    private int doctorID;

    @JsonProperty("person")
    @XmlElement(name = "person", type = Person.class)
    private Person person;

    @JsonProperty("specialty")
    @XmlElement(name = "specialty", type = Specialty.class)
    private Specialty specialty;

    @JsonProperty("years_of_experience")
    @XmlElement(name = "years_of_experience")
    private int yearsOfExperience;

    public Doctor() {
    }

    public Doctor(Person person, Specialty specialty, int yearsOfExperience) {
        this.person = person;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", person=" + person +
                ", specialty=" + specialty +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
