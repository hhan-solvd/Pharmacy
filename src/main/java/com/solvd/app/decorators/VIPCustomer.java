package com.solvd.app.decorators;

import com.solvd.app.interfaces.ICustomerDecorator;
import com.solvd.app.models.Person;

public class VIPCustomer extends CustomerDecorator {

    private double discountPercentage;

    private VIPCustomer(Builder builder) {
        super(builder.customer);
        this.discountPercentage = builder.discountPercentage;
    }

    public static class Builder {
        private ICustomerDecorator customer;
        private double discountPercentage;

        public Builder withCustomer(ICustomerDecorator customer) {
            this.customer = customer;
            return this;
        }

        public Builder withDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public VIPCustomer build() {
            return new VIPCustomer(this);
        }
    }

    @Override
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public int getCustomerID() {
        return super.getCustomerID();
    }

    @Override
    public Person getPerson() {
        return super.getPerson();
    }

    @Override
    public String toString() {
        return super.toString() + ", discountPercentage=" + discountPercentage;
    }
}