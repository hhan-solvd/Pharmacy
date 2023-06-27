package com.solvd.app.interfaces;

import com.solvd.app.models.Person;

public interface ICustomerDecorator {

    int getCustomerID();

    Person getPerson();

    double getDiscountPercentage();
}
