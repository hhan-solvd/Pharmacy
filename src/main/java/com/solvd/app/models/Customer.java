package com.solvd.app.models;

public class Customer {
    private int customerID;
    private Person person;

    public Customer() {
    }

    public Customer(Person person) {
        this.person = person;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", person=" + person +
                '}';
    }
}
