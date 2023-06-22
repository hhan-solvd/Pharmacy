package com.solvd.app.jdbc;

import com.solvd.app.interfaces.IInventoryDAO;
import com.solvd.app.models.Inventory;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements IInventoryDAO {

    private static final Logger LOGGER = LogManager.getLogger(InventoryDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private DrugDAO drugDAO = new DrugDAO();
    private PharmacyDAO pharmacyDAO = new PharmacyDAO();

    @Override
    public void createEntity(Inventory inventory) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO inventory (quantity, pharmacy_id, drug_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, inventory.getQuantity());
            preparedStatement.setInt(2, inventory.getPharmacy().getPharmacyID());
            preparedStatement.setInt(3, inventory.getDrug().getDrugID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    inventory.setInventoryID(newKey.getInt(1));
                    LOGGER.info("Created a new inventory with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create inventory: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Inventory getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Inventory inventory = new Inventory();

        try {
            String sql = "SELECT * FROM inventory WHERE inventory_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                inventory.setInventoryID(resultSet.getInt("inventory_id"));
                inventory.setQuantity(resultSet.getInt("quantity"));
                inventory.setPharmacy(pharmacyDAO.getEntityByID(resultSet.getInt("pharmacy_id")));
                inventory.setDrug(drugDAO.getEntityByID(resultSet.getInt("drug_id")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get inventory: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return inventory;
    }

    @Override
    public void updateEntity(Inventory inventory) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE inventory SET quantity = ?, pharmacy_id = ?, drug_id = ? WHERE inventory_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, inventory.getQuantity());
            preparedStatement.setInt(2, inventory.getPharmacy().getPharmacyID());
            preparedStatement.setInt(3, inventory.getDrug().getDrugID());
            preparedStatement.setInt(4, inventory.getInventoryID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the inventory with ID number " + inventory.getInventoryID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update inventory: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM inventory WHERE inventory_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the inventory with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete inventory: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Inventory> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Inventory> inventoryList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM inventory";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryID(resultSet.getInt("inventory_id"));
                inventory.setQuantity(resultSet.getInt("quantity"));
                inventory.setPharmacy(pharmacyDAO.getEntityByID(resultSet.getInt("pharmacy_id")));
                inventory.setDrug(drugDAO.getEntityByID(resultSet.getInt("drug_id")));

                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all inventories: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return inventoryList;
    }

    @Override
    public int getInventoryQuantityByID(int id) {
        Connection connection = connectionPool.getConnection();
        int quantity = 0;

        try {
            String sql = "SELECT quantity FROM inventory WHERE inventory_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get quantity of inventory: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return quantity;
    }
}
