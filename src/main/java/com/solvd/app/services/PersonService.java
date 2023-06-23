package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IPersonDAO;
import com.solvd.app.models.Person;

import java.util.List;

public class PersonService {

    private IPersonDAO personDAO;

    public PersonService(DAOType type) {
        this.personDAO = (IPersonDAO) DBFactoryGenerator.getFactory(type).getDAO("Person");
    }

    public void createPerson(Person person) {
        personDAO.createEntity(person);
    }

    public Person getPersonByID(int id) {
        return personDAO.getEntityByID(id);
    }

    public Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) {
        return personDAO.getPersonByNameAndPhoneNumber(name, phoneNumber);
    }

    public void updatePerson(Person person) {
        personDAO.updateEntity(person);
    }

    public void deletePersonByID(int id) {
        personDAO.deleteEntityByID(id);
    }

    public List<Person> getAllPersons() {
        return personDAO.getAll();
    }
}