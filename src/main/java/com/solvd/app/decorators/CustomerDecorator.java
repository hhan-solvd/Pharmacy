package com.solvd.app.decorators;

import com.solvd.app.interfaces.ICustomerDecorator;
import com.solvd.app.models.Person;

public class CustomerDecorator implements ICustomerDecorator {
    protected ICustomerDecorator customer;

    public CustomerDecorator(ICustomerDecorator customer) {
        this.customer = customer;
    }

    @Override
    public int getCustomerID() {
        return customer.getCustomerID();
    }

    @Override
    public Person getPerson() {
        return customer.getPerson();
    }

    @Override
    public double getDiscountPercentage() {
        return customer.getDiscountPercentage();
    }
}

