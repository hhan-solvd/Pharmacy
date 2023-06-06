package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Supplier;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements IBaseDAO<Supplier> {
    private static final Logger LOGGER = LogManager.getLogger(SupplierDAO.class);
    private ConnectionPool connectionPool;

    public SupplierDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(Supplier supplier) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO suppliers (name, address, phone_number) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getAddress());
        preparedStatement.setInt(3, supplier.getPhoneNumber());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                supplier.setSupplierID(newKey.getInt(1));
                LOGGER.info("Created a new supplier with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }


    public Supplier getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Supplier supplier = new Supplier();
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            supplier.setSupplierID(resultSet.getInt("supplier_id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setAddress(resultSet.getString("address"));
            supplier.setPhoneNumber(resultSet.getInt("phone_number"));
        }

        connectionPool.releaseConnection(connection);

        return supplier;
    }

    public void updateEntity(Supplier supplier) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE suppliers SET name = ?, address = ?, phone_number = ? WHERE supplier_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getAddress());
        preparedStatement.setInt(3, supplier.getPhoneNumber());
        preparedStatement.setInt(4, supplier.getSupplierID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the supplier with ID number " + supplier.getSupplierID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the supplier with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Supplier> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Supplier> supplierList = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupplierID(resultSet.getInt("supplier_id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setAddress(resultSet.getString("address"));
            supplier.setPhoneNumber(resultSet.getInt("phone_number"));

            supplierList.add(supplier);
        }

        connectionPool.releaseConnection(connection);

        return supplierList;
    }
}
