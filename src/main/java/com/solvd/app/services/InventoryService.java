package com.solvd.app.services;

import com.solvd.app.daos.InventoryDAO;
import com.solvd.app.models.Inventory;

import java.sql.SQLException;
import java.util.List;

public class InventoryService {
    private InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void createInventory(Inventory inventory) throws SQLException {
        inventoryDAO.createEntity(inventory);
    }

    public Inventory getInventoryByID(int id) throws SQLException {
        return inventoryDAO.getEntityByID(id);
    }

    public void updateInventory(Inventory inventory) throws SQLException {
        inventoryDAO.updateEntity(inventory);
    }

    public void deleteInventoryByID(int id) throws SQLException {
        inventoryDAO.deleteEntityByID(id);
    }

    public List<Inventory> getAllInventories() throws SQLException {
        return inventoryDAO.getAll();
    }
}
