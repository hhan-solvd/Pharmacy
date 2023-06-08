package com.solvd.app.services;

import com.solvd.app.dao.InventoryDAO;
import com.solvd.app.models.Inventory;

import java.util.List;

public class InventoryService {
    private InventoryDAO inventoryDAO = new InventoryDAO();

    public void createInventory(Inventory inventory) {
        inventoryDAO.createEntity(inventory);
    }

    public Inventory getInventoryByID(int id) {
        return inventoryDAO.getEntityByID(id);
    }

    public void updateInventory(Inventory inventory) {
        inventoryDAO.updateEntity(inventory);
    }

    public void deleteInventoryByID(int id) {
        inventoryDAO.deleteEntityByID(id);
    }

    public List<Inventory> getAllInventories() {
        return inventoryDAO.getAll();
    }
}
