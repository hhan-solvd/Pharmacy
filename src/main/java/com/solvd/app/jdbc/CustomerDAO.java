package com.solvd.app.jdbc;

import com.solvd.app.interfaces.ICustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private PersonDAO personDAO = new PersonDAO();

    @Override
    public void createEntity(Customer customer) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO customers (person_id, discount_percentage) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, customer.getPerson().getPersonID());
            preparedStatement.setDouble(2, customer.getDiscountPercentage());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    customer.setCustomerID(newKey.getInt(1));
                    LOGGER.info("Created a new customer with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create customer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Customer getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Customer customer = new Customer();

        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                customer.setDiscountPercentage(resultSet.getDouble("discount_percentage"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get customer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return customer;
    }

    @Override
    public void updateEntity(Customer customer) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE customers SET person_id = ?, discount_percentage = ? WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getPerson().getPersonID());
            preparedStatement.setDouble(2, customer.getDiscountPercentage());
            preparedStatement.setInt(3, customer.getCustomerID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the customer with ID number " + customer.getCustomerID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update customer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the customer with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete customer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Customer> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Customer> customerList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                customer.setDiscountPercentage(resultSet.getDouble("discount_percentage"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all customers: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return customerList;
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        Connection connection = connectionPool.getConnection();
        List<Customer> customerList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM customers c INNER JOIN people p ON c.person_id = p.person_id WHERE p.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                customer.setDiscountPercentage(resultSet.getDouble("discount_percentage"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get customers by name: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return customerList;
    }
}
