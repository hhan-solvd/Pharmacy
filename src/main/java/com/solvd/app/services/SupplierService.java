package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.ISupplierDAO;
import com.solvd.app.models.Supplier;

import java.util.List;

public class SupplierService {

    private ISupplierDAO supplierDAO;

    public SupplierService(DAOType type) {
        this.supplierDAO = DBFactoryGenerator.getFactory(type).getSupplierDAO();
    }

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

    public Supplier getSupplierByAddress(String address) {
        return supplierDAO.getSupplierByAddress(address);
    }
}
