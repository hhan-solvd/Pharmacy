package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @JsonProperty("customer_id")
    @XmlAttribute(name = "customer_id")
    private int customerID;

    @JsonProperty("person")
    @XmlElement(name = "person", type = Person.class)
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
