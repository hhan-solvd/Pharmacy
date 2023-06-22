package com.solvd.app.services;

import com.solvd.app.interfaces.ICustomerDAO;
import com.solvd.app.jdbc.CustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.mybatis.MyBatisCustomerDAO;

import java.util.List;

public class CustomerService {

    private ICustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public CustomerService(MyBatisCustomerDAO myBatisCustomerDAO) {
        this.customerDAO = myBatisCustomerDAO;
    }

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

    public List<Customer> getCustomersByName(String name) {
        return customerDAO.getCustomersByName(name);
    }
}