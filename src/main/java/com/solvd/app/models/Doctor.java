package com.solvd.app.models;

public class Doctor {
    private int doctorID;
    private Person person;
    private Specialty specialty;
    private int yearsOfExperience;

    public Doctor() {
    }

    public Doctor(Person person, Specialty specialty, int yearsOfExperience) {
        this.person = person;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Doctor(int doctorID, Person person, Specialty specialty, int yearsOfExperience) {
        this.doctorID = doctorID;
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
