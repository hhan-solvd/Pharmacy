package com.solvd.app.jdbc;

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
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void createEntity(Person person) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO people (name, address, phone_number, email, gender) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getAddress());
            preparedStatement.setInt(3, person.getPhoneNumber());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getGender());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    person.setPersonID(newKey.getInt(1));
                    LOGGER.info("Created a new person with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create person: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Person getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Person person = new Person();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get person: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return person;
    }

    @Override
    public Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) {
        Connection connection = connectionPool.getConnection();
        Person person = new Person();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get person by name and phone number: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return person;
    }

    @Override
    public void updateEntity(Person person) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE people SET name = ?, address = ?, phone_number = ?, email = ?, gender = ? WHERE person_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getAddress());
            preparedStatement.setInt(3, person.getPhoneNumber());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getGender());
            preparedStatement.setInt(6, person.getPersonID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the person with ID number " + person.getPersonID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update person: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM people WHERE person_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the person with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete person: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Person> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Person> personList = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all people: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return personList;
    }
}
