package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.InventoryDAO;
import com.solvd.app.interfaces.IInventoryDAO;
import com.solvd.app.mybatis.MyBatisInventoryDAO;
import com.solvd.app.models.Inventory;

import java.util.List;

public class InventoryService {

    private IInventoryDAO inventoryDAO;

    public InventoryService(DAOType type) {
        switch (type) {
            case JDBC -> this.inventoryDAO = new InventoryDAO();
            case MYBATIS -> this.inventoryDAO = new MyBatisInventoryDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

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

    public int getInventoryQuantityByID(int id) {
        return inventoryDAO.getInventoryQuantityByID(id);
    }
}
