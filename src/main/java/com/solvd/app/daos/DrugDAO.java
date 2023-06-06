package com.solvd.app.daos;

import com.solvd.app.interfaces.IDrugDAO;
import com.solvd.app.models.*;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO implements IDrugDAO {
    private static final Logger LOGGER = LogManager.getLogger(DrugDAO.class);
    private ConnectionPool connectionPool;
    private SupplierDAO supplierDAO;
    private ManufacturerDAO manufacturerDAO;
    private DrugCategoryDAO drugCategoryDAO;

    public DrugDAO(ConnectionPool connectionPool, SupplierDAO supplierDAO, ManufacturerDAO manufacturerDAO,
                   DrugCategoryDAO drugCategoryDAO) {
        this.connectionPool = connectionPool;
        this.supplierDAO = supplierDAO;
        this.manufacturerDAO = manufacturerDAO;
        this.drugCategoryDAO = drugCategoryDAO;
    }

    public void createEntity(Drug drug) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO drugs (name, supplier_id, manufacturer_id, category_id, price) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, drug.getName());
        preparedStatement.setInt(2, drug.getSupplier().getSupplierID());
        preparedStatement.setInt(3, drug.getManufacturer().getManufacturerID());
        preparedStatement.setInt(4, drug.getDrugCategory().getCategoryID());
        preparedStatement.setDouble(5, drug.getPrice());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                drug.setDrugID(newKey.getInt(1));
                LOGGER.info("Created a new drug with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Drug getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Drug drug = new Drug();
        String sql = "SELECT * FROM drugs WHERE drug_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            drug.setDrugID(resultSet.getInt("drug_id"));
            drug.setName(resultSet.getString("name"));
            drug.setSupplier(supplierDAO.getEntityByID(resultSet.getInt("supplier_id")));
            drug.setManufacturer(manufacturerDAO.getEntityByID(resultSet.getInt("manufacturer_id")));
            drug.setDrugCategory(drugCategoryDAO.getEntityByID(resultSet.getInt("category_id")));
            drug.setPrice(resultSet.getDouble("price"));
        }

        connectionPool.releaseConnection(connection);

        return drug;
    }

    public void updateEntity(Drug drug) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE drugs SET name = ?, supplier_id = ?, manufacturer_id = ?, category_id = ?, " +
                "price = ? WHERE drug_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, drug.getName());
        preparedStatement.setInt(2, drug.getSupplier().getSupplierID());
        preparedStatement.setInt(3, drug.getManufacturer().getManufacturerID());
        preparedStatement.setInt(4, drug.getDrugCategory().getCategoryID());
        preparedStatement.setDouble(5, drug.getPrice());
        preparedStatement.setInt(6, drug.getDrugID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the drug with ID number " + drug.getDrugID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM drugs WHERE drug_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the drug with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Drug> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Drug> drugList = new ArrayList<>();

        String sql = "SELECT * FROM drugs";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Drug drug = new Drug();
            drug.setDrugID(resultSet.getInt("drug_id"));
            drug.setName(resultSet.getString("name"));
            drug.setSupplier(supplierDAO.getEntityByID(resultSet.getInt("supplier_id")));
            drug.setManufacturer(manufacturerDAO.getEntityByID(resultSet.getInt("manufacturer_id")));
            drug.setDrugCategory(drugCategoryDAO.getEntityByID(resultSet.getInt("category_id")));
            drug.setPrice(resultSet.getDouble("price"));

            drugList.add(drug);
        }

        connectionPool.releaseConnection(connection);

        return drugList;
    }

    public List<Drug> getDrugsByManufacturer(Manufacturer manufacturer) throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Drug> drugList = new ArrayList<>();

        String sql = "SELECT * FROM drugs WHERE manufacturer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, manufacturer.getManufacturerID());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Drug drug = new Drug();
            drug.setDrugID(resultSet.getInt("drug_id"));
            drug.setName(resultSet.getString("name"));
            drug.setSupplier(supplierDAO.getEntityByID(resultSet.getInt("supplier_id")));
            drug.setManufacturer(manufacturerDAO.getEntityByID(resultSet.getInt("manufacturer_id")));
            drug.setDrugCategory(drugCategoryDAO.getEntityByID(resultSet.getInt("category_id")));
            drug.setPrice(resultSet.getDouble("price"));

            drugList.add(drug);
        }

        connectionPool.releaseConnection(connection);

        return drugList;
    }
}
