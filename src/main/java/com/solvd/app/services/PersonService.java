package com.solvd.app.services;

import com.solvd.app.dao.PersonDAO;
import com.solvd.app.models.Person;

import java.util.List;

public class PersonService {
    private PersonDAO personDAO = new PersonDAO();

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
