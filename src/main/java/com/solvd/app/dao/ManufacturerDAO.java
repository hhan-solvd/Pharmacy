package com.solvd.app.dao;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Manufacturer;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAO implements IBaseDAO<Manufacturer> {
    private static final Logger LOGGER = LogManager.getLogger(ManufacturerDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public void createEntity(Manufacturer manufacturer) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO manufacturers (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, manufacturer.getName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    manufacturer.setManufacturerID(newKey.getInt(1));
                    LOGGER.info("Created a new manufacturer with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create manufacturer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Manufacturer getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Manufacturer manufacturer = new Manufacturer();

        try {
            String sql = "SELECT * FROM manufacturers WHERE manufacturer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                manufacturer.setManufacturerID(resultSet.getInt("manufacturer_id"));
                manufacturer.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get manufacturer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return manufacturer;
    }

    public void updateEntity(Manufacturer manufacturer) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE manufacturers SET name = ? WHERE manufacturer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setInt(2, manufacturer.getManufacturerID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the manufacturer with ID number " + manufacturer.getManufacturerID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update manufacturer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM manufacturers WHERE manufacturer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the manufacturer with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete manufacturer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<Manufacturer> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Manufacturer> manufacturerList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM manufacturers";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturerID(resultSet.getInt("manufacturer_id"));
                manufacturer.setName(resultSet.getString("name"));

                manufacturerList.add(manufacturer);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all manufacturers: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return manufacturerList;
    }
}
