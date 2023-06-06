package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements IBaseDAO<Customer> {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private ConnectionPool connectionPool;
    private PersonDAO personDAO;

    public CustomerDAO(ConnectionPool connectionPool, PersonDAO personDAO) {
        this.connectionPool = connectionPool;
        this.personDAO = personDAO;
    }

    public void createEntity(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO customers (person_id) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, customer.getPerson().getPersonID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                customer.setCustomerID(newKey.getInt(1));
                LOGGER.info("Created a new customer with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Customer getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Customer customer = new Customer();
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            customer.setCustomerID(resultSet.getInt("customer_id"));
            customer.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
        }

        connectionPool.releaseConnection(connection);

        return customer;
    }

    public void updateEntity(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE customers SET person_id = ? WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getPerson().getPersonID());
        preparedStatement.setInt(2, customer.getCustomerID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the customer with ID number " + customer.getCustomerID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM customers WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the customer with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Customer> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT * FROM customers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomerID(resultSet.getInt("customer_id"));
            customer.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));

            customerList.add(customer);
        }

        connectionPool.releaseConnection(connection);

        return customerList;
    }
}
