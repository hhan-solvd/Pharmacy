package com.solvd.app.services;

import com.solvd.app.jdbc.SupplierDAO;
import com.solvd.app.interfaces.ISupplierDAO;
import com.solvd.app.mybatis.MyBatisSupplierDAO;
import com.solvd.app.models.Supplier;

import java.util.List;

public class SupplierService {

    private ISupplierDAO supplierDAO;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
    }

    public SupplierService(MyBatisSupplierDAO myBatisSupplierDAO) {
        this.supplierDAO = myBatisSupplierDAO;
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
