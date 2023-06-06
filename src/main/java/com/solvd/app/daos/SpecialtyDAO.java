package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Specialty;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO implements IBaseDAO<Specialty> {
    private static final Logger LOGGER = LogManager.getLogger(SpecialtyDAO.class);
    private ConnectionPool connectionPool;

    public SpecialtyDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(Specialty specialty) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO specialties (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, specialty.getName());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                specialty.setSpecialtyID(newKey.getInt(1));
                LOGGER.info("Created a new specialty with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Specialty getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Specialty specialty = new Specialty();
        String sql = "SELECT * FROM specialties WHERE specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            specialty.setSpecialtyID(resultSet.getInt("specialty_id"));
            specialty.setName(resultSet.getString("name"));
        }

        connectionPool.releaseConnection(connection);

        return specialty;
    }

    public void updateEntity(Specialty specialty) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE specialties SET name = ? WHERE specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, specialty.getName());
        preparedStatement.setInt(2, specialty.getSpecialtyID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the specialty with ID number " + specialty.getSpecialtyID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM specialties WHERE specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the specialty with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Specialty> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Specialty> specialtyList = new ArrayList<>();

        String sql = "SELECT * FROM specialties";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Specialty specialty = new Specialty();
            specialty.setSpecialtyID(resultSet.getInt("specialty_id"));
            specialty.setName(resultSet.getString("name"));

            specialtyList.add(specialty);
        }

        connectionPool.releaseConnection(connection);

        return specialtyList;
    }
}
