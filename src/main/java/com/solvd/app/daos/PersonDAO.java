package com.solvd.app.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.solvd.app.interfaces.IPersonDAO;
import com.solvd.app.utils.ConnectionPool;
import com.solvd.app.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonDAO implements IPersonDAO {
    private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class);
    private ConnectionPool connectionPool;

    public PersonDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(Person person) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO people (name, address, phone_number, email, gender) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getAddress());
        preparedStatement.setInt(3, person.getPhoneNumber());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getGender());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                person.setPersonID(newKey.getInt(1));
                LOGGER.info("Created a new person with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Person getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Person person = new Person();
        String sql = "SELECT * FROM people WHERE person_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            person.setPersonID(resultSet.getInt("person_id"));
            person.setName(resultSet.getString("name"));
            person.setAddress(resultSet.getString("address"));
            person.setPhoneNumber(resultSet.getInt("phone_number"));
            person.setEmail(resultSet.getString("email"));
            person.setGender(resultSet.getString("gender"));
        }

        connectionPool.releaseConnection(connection);

        return person;
    }

    public Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Person person = new Person();
        String sql = "SELECT * FROM people WHERE name = ? AND phone_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            person.setPersonID(resultSet.getInt("person_id"));
            person.setName(resultSet.getString("name"));
            person.setAddress(resultSet.getString("address"));
            person.setPhoneNumber(resultSet.getInt("phone_number"));
            person.setEmail(resultSet.getString("email"));
            person.setGender(resultSet.getString("gender"));
        }

        connectionPool.releaseConnection(connection);

        return person;
    }

    public void updateEntity(Person person) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE people SET name = ?, address = ?, phone_number = ?, email = ?, gender = ? " +
                "WHERE person_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getAddress());
        preparedStatement.setInt(3, person.getPhoneNumber());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getGender());
        preparedStatement.setInt(6, person.getPersonID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the person with ID number " + person.getPersonID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM people WHERE person_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the person with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Person> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM people";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = new Person();
            person.setPersonID(resultSet.getInt("person_id"));
            person.setName(resultSet.getString("name"));
            person.setAddress(resultSet.getString("address"));
            person.setPhoneNumber(resultSet.getInt("phone_number"));
            person.setEmail(resultSet.getString("email"));
            person.setGender(resultSet.getString("gender"));

            personList.add(person);
        }

        connectionPool.releaseConnection(connection);

        return personList;
    }
}
