package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Inventory;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements IBaseDAO<Inventory> {
    private static final Logger LOGGER = LogManager.getLogger(InventoryDAO.class);
    private ConnectionPool connectionPool;
    private DrugDAO drugDAO;
    private PharmacyDAO pharmacyDAO;

    public InventoryDAO(ConnectionPool connectionPool, DrugDAO drugDAO, PharmacyDAO pharmacyDAO) {
        this.connectionPool = connectionPool;
        this.drugDAO = drugDAO;
        this.pharmacyDAO = pharmacyDAO;
    }

    public void createEntity(Inventory inventory) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO inventory (quantity, pharmacy_id, drug_id) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, inventory.getQuantity());
        preparedStatement.setInt(2, inventory.getPharmacy().getPharmacyID());
        preparedStatement.setInt(3, inventory.getDrug().getDrugID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                inventory.setInventoryID(newKey.getInt(1));
                LOGGER.info("Created a new inventory with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Inventory getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Inventory inventory = new Inventory();
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

        connectionPool.releaseConnection(connection);

        return inventory;
    }

    public void updateEntity(Inventory inventory) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE inventory SET quantity = ?, pharmacy_id = ?, drug_id = ? WHERE inventory_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, inventory.getQuantity());
        preparedStatement.setInt(2, inventory.getPharmacy().getPharmacyID());
        preparedStatement.setInt(3, inventory.getDrug().getDrugID());
        preparedStatement.setInt(4, inventory.getInventoryID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the inventory with ID number " + inventory.getInventoryID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM inventory WHERE inventory_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the inventory with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Inventory> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Inventory> inventoryList = new ArrayList<>();

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

        connectionPool.releaseConnection(connection);

        return inventoryList;
    }
}
