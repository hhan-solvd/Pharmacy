package com.solvd.app.models;

public class Specialty {
    private int specialtyID;
    private String name;

    public Specialty() {
    }

    public Specialty(String name) {
        this.name = name;
    }

    public Specialty(int specialtyID, String name) {
        this.specialtyID = specialtyID;
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
