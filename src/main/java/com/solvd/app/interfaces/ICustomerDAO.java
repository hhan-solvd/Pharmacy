package com.solvd.app.interfaces;

import com.solvd.app.models.Customer;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {

    List<Customer> getCustomersByName(String name);
}
