package com.solvd.app.models;

public class Staff {
    private int staffID;
    private Person person;
    private Pharmacy pharmacy;
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
