package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.app.interfaces.ICustomerDecorator;

import javax.xml.bind.annotation.*;

@JsonRootName("customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements ICustomerDecorator {

    @JsonProperty("customer_id")
    @XmlAttribute(name = "customer_id")
    private int customerID;

    @JsonProperty("person")
    @XmlElement(name = "person", type = Person.class)
    private Person person;

    @JsonProperty("discount_percentage")
    @XmlElement(name = "discount_percentage")
    private double discountPercentage;

    public Customer() {
    }

    private Customer(Builder builder) {
        this.customerID = builder.customerID;
        this.person = builder.person;
        this.discountPercentage = builder.discountPercentage;
    }

    public static class Builder {
        private int customerID;
        private Person person;
        private double discountPercentage;

        public Builder withCustomerID(int customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder withPerson(Person person) {
            this.person = person;
            return this;
        }

        public Builder withDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
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

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", person=" + person +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
