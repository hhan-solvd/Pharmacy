package com.solvd.app.interfaces;

import com.solvd.app.models.Person;

public interface IPersonDAO extends IBaseDAO<Person> {

    Person getPersonByNameAndPhoneNumber(String name, int phoneNumber);
}
