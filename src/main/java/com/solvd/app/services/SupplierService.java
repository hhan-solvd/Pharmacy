package com.solvd.app.services;

import com.solvd.app.dao.SupplierDAO;
import com.solvd.app.models.Supplier;

import java.util.List;

public class SupplierService {
    private SupplierDAO supplierDAO = new SupplierDAO();

    public void createSupplier(Supplier supplier) {
        supplierDAO.createEntity(supplier);
    }

    public Supplier getSupplierByID(int id) {
        return supplierDAO.getEntityByID(id);
    }

    public void updateSupplier(Supplier supplier) {
        supplierDAO.updateEntity(supplier);
    }

    public void deleteSupplierByID(int id) {
        supplierDAO.deleteEntityByID(id);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAll();
    }
}
