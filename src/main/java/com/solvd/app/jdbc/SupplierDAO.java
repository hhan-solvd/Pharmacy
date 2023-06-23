package com.solvd.app.jdbc;

import com.solvd.app.interfaces.ISupplierDAO;
import com.solvd.app.models.Supplier;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements ISupplierDAO {

    private static final Logger LOGGER = LogManager.getLogger(SupplierDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void createEntity(Supplier supplier) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO suppliers (name, address, phone_number) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setInt(3, supplier.getPhoneNumber());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating supplier failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    supplier.setSupplierID(newKey.getInt(1));
                    LOGGER.info("Created a new supplier with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating supplier failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create supplier: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Supplier getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Supplier supplier = new Supplier();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get supplier: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return supplier;
    }

    @Override
    public void updateEntity(Supplier supplier) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE suppliers SET name = ?, address = ?, phone_number = ? WHERE supplier_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setInt(3, supplier.getPhoneNumber());
            preparedStatement.setInt(4, supplier.getSupplierID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating supplier failed, no rows affected.");
            } else {
                LOGGER.info("Updated the supplier with ID number " + supplier.getSupplierID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update supplier: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting supplier failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the supplier with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete supplier: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Supplier> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Supplier> supplierList = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all suppliers: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return supplierList;
    }

    @Override
    public Supplier getSupplierByAddress(String address) {
        Connection connection = connectionPool.getConnection();
        Supplier supplier = new Supplier();

        try {
            String sql = "SELECT * FROM suppliers WHERE address = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                supplier.setSupplierID(resultSet.getInt("supplier_id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));
                supplier.setPhoneNumber(resultSet.getInt("phone_number"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get supplier by address: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return supplier;
    }
}
