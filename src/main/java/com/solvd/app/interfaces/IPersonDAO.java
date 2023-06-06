package com.solvd.app.interfaces;

import com.solvd.app.models.Person;

import java.sql.SQLException;

public interface IPersonDAO extends IBaseDAO<Person> {
    Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) throws SQLException;
}
