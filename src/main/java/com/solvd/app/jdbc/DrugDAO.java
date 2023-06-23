package com.solvd.app.jdbc;

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
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private SupplierDAO supplierDAO = new SupplierDAO();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    private DrugCategoryDAO drugCategoryDAO = new DrugCategoryDAO();

    @Override
    public void createEntity(Drug drug) {
        Connection connection = connectionPool.getConnection();

        try {
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
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    drug.setDrugID(newKey.getInt(1));
                    LOGGER.info("Created a new drug with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create drug: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Drug getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Drug drug = new Drug();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get drug: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drug;
    }

    @Override
    public void updateEntity(Drug drug) {
        Connection connection = connectionPool.getConnection();

        try {
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
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the drug with ID number " + drug.getDrugID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update drug: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM drugs WHERE drug_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the drug with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete drug: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Drug> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Drug> drugList = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all drugs: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drugList;
    }

    @Override
    public List<Drug> getDrugsByManufacturer(Manufacturer manufacturer) {
        Connection connection = connectionPool.getConnection();
        List<Drug> drugList = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get drugs by manufacturer: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drugList;
    }
}
