package com.solvd.app.services;

import com.solvd.app.daos.PersonDAO;
import com.solvd.app.models.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void createPerson(Person person) throws SQLException {
        personDAO.createEntity(person);
    }

    public Person getPersonByID(int id) throws SQLException {
        return personDAO.getEntityByID(id);
    }

    public Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) throws SQLException {
        return personDAO.getPersonByNameAndPhoneNumber(name, phoneNumber);
    }

    public void updatePerson(Person person) throws SQLException {
        personDAO.updateEntity(person);
    }

    public void deletePersonByID(int id) throws SQLException {
        personDAO.deleteEntityByID(id);
    }

    public List<Person> getAllPersons() throws SQLException {
        return personDAO.getAll();
    }
}
