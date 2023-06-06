package com.solvd.app.services;

import com.solvd.app.daos.CustomerDAO;
import com.solvd.app.models.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void createCustomer(Customer customer) throws SQLException {
        customerDAO.createEntity(customer);
    }

    public Customer getCustomerByID(int id) throws SQLException {
        return customerDAO.getEntityByID(id);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateEntity(customer);
    }

    public void deleteCustomerByID(int id) throws SQLException {
        customerDAO.deleteEntityByID(id);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAll();
    }
}
