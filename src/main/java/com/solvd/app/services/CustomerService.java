package com.solvd.app.services;

import com.solvd.app.dao.CustomerDAO;
import com.solvd.app.models.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void createCustomer(Customer customer) {
        customerDAO.createEntity(customer);
    }

    public Customer getCustomerByID(int id) {
        return customerDAO.getEntityByID(id);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateEntity(customer);
    }

    public void deleteCustomerByID(int id) {
        customerDAO.deleteEntityByID(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
}
