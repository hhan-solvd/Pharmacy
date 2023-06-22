package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.SupplierDAO;
import com.solvd.app.interfaces.ISupplierDAO;
import com.solvd.app.mybatis.MyBatisSupplierDAO;
import com.solvd.app.models.Supplier;

import java.util.List;

public class SupplierService {

    private ISupplierDAO supplierDAO;

    public SupplierService(DAOType type) {
        switch (type) {
            case JDBC -> this.supplierDAO = new SupplierDAO();
            case MYBATIS -> this.supplierDAO = new MyBatisSupplierDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
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
