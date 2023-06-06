package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDAO implements IBaseDAO<Pharmacy> {
    private static final Logger LOGGER = LogManager.getLogger(PharmacyDAO.class);
    private ConnectionPool connectionPool;

    public PharmacyDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(Pharmacy pharmacy) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO pharmacies (name, address, phone_number) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, pharmacy.getName());
        preparedStatement.setString(2, pharmacy.getAddress());
        preparedStatement.setInt(3, pharmacy.getPhoneNumber());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                pharmacy.setPharmacyID(newKey.getInt(1));
                LOGGER.info("Created a new pharmacy with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }


    public Pharmacy getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Pharmacy pharmacy = new Pharmacy();
        String sql = "SELECT * FROM pharmacies WHERE pharmacy_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            pharmacy.setPharmacyID(resultSet.getInt("pharmacy_id"));
            pharmacy.setName(resultSet.getString("name"));
            pharmacy.setAddress(resultSet.getString("address"));
            pharmacy.setPhoneNumber(resultSet.getInt("phone_number"));
        }

        connectionPool.releaseConnection(connection);

        return pharmacy;
    }

    public void updateEntity(Pharmacy pharmacy) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE pharmacies SET name = ?, address = ?, phone_number = ? WHERE pharmacy_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pharmacy.getName());
        preparedStatement.setString(2, pharmacy.getAddress());
        preparedStatement.setInt(3, pharmacy.getPhoneNumber());
        preparedStatement.setInt(4, pharmacy.getPharmacyID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the pharmacy with ID number " + pharmacy.getPharmacyID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM pharmacies WHERE pharmacy_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the pharmacy with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Pharmacy> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Pharmacy> pharmacyList = new ArrayList<>();

        String sql = "SELECT * FROM pharmacies";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setPharmacyID(resultSet.getInt("pharmacy_id"));
            pharmacy.setName(resultSet.getString("name"));
            pharmacy.setAddress(resultSet.getString("address"));
            pharmacy.setPhoneNumber(resultSet.getInt("phone_number"));

            pharmacyList.add(pharmacy);
        }

        connectionPool.releaseConnection(connection);

        return pharmacyList;
    }
}
