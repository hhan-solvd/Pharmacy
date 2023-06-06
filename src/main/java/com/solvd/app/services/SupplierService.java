package com.solvd.app.services;

import com.solvd.app.daos.SupplierDAO;
import com.solvd.app.models.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierService {
    private SupplierDAO supplierDAO;

    public SupplierService(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public void createSupplier(Supplier supplier) throws SQLException {
        supplierDAO.createEntity(supplier);
    }

    public Supplier getSupplierByID(int id) throws SQLException {
        return supplierDAO.getEntityByID(id);
    }

    public void updateSupplier(Supplier supplier) throws SQLException {
        supplierDAO.updateEntity(supplier);
    }

    public void deleteSupplierByID(int id) throws SQLException {
        supplierDAO.deleteEntityByID(id);
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierDAO.getAll();
    }
}
