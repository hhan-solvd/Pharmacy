package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IInventoryDAO;
import com.solvd.app.models.Inventory;

import java.util.List;

public class InventoryService {

    private IInventoryDAO inventoryDAO;
    private DrugService drugService;
    private PharmacyService pharmacyService;

    public InventoryService(DAOType type) {
        this.inventoryDAO = DBFactoryGenerator.getFactory(type).getInventoryDAO();
        this.drugService = new DrugService(type);
        this.pharmacyService = new PharmacyService(type);
    }

    public void createInventory(Inventory inventory) {
        if (inventory.getDrug().getDrugID() == 0) {
            drugService.createDrug(inventory.getDrug());
        }

        if (inventory.getPharmacy().getPharmacyID() == 0) {
            pharmacyService.createPharmacy(inventory.getPharmacy());
        }

        inventoryDAO.createEntity(inventory);
    }

    public Inventory getInventoryByID(int id) {
        return inventoryDAO.getEntityByID(id);
    }

    public void updateInventory(Inventory inventory) {
        if (inventory.getDrug().getDrugID() == 0) {
            drugService.createDrug(inventory.getDrug());
        }

        if (inventory.getPharmacy().getPharmacyID() == 0) {
            pharmacyService.createPharmacy(inventory.getPharmacy());
        }

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
