package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.decorators.VIPCustomer;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.ICustomerDAO;
import com.solvd.app.models.Customer;

import java.util.List;

public class CustomerService {

    private ICustomerDAO customerDAO;
    private PersonService personService;

    public CustomerService(DAOType type) {
        this.customerDAO = (ICustomerDAO) DBFactoryGenerator.getFactory(type).getDAO("Customer");
        this.personService = new PersonService(type);
    }

    public void createCustomer(Customer customer) {
        if (customer.getPerson().getPersonID() == 0) {
            personService.createPerson(customer.getPerson());
        }
        customerDAO.createEntity(customer);
    }

    public void createCustomer(VIPCustomer vipCustomer) {
        Customer customer = new Customer.Builder()
                .withCustomerID(vipCustomer.getCustomerID())
                .withPerson(vipCustomer.getPerson())
                .withDiscountPercentage(vipCustomer.getDiscountPercentage())
                .build();
        customerDAO.createEntity(customer);
    }

    public Customer getCustomerByID(int id) {
        return customerDAO.getEntityByID(id);
    }

    public void updateCustomer(Customer customer) {
        if (customer.getPerson().getPersonID() == 0) {
            personService.createPerson(customer.getPerson());
        }
        customerDAO.updateEntity(customer);
    }

    public void deleteCustomerByID(int id) {
        customerDAO.deleteEntityByID(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public List<Customer> getCustomersByName(String name) {
        return customerDAO.getCustomersByName(name);
    }
}
