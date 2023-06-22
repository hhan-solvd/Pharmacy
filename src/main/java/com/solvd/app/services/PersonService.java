package com.solvd.app.services;

import com.solvd.app.jdbc.PersonDAO;
import com.solvd.app.interfaces.IPersonDAO;
import com.solvd.app.mybatis.MyBatisPersonDAO;
import com.solvd.app.models.Person;

import java.util.List;

public class PersonService {

    private IPersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public PersonService(MyBatisPersonDAO myBatisPersonDAO) {
        this.personDAO = myBatisPersonDAO;
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