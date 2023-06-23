package com.solvd.app.daofactories;

import com.solvd.app.interfaces.*;
import com.solvd.app.jdbc.*;

public class JDBCFactory extends AbstractDAOFactory {

    @Override
    public ICustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    @Override
    public IDoctorDAO getDoctorDAO() {
        return new DoctorDAO();
    }

    @Override
    public IDrugCategoryDAO getDrugCategoryDAO() {
        return new DrugCategoryDAO();
    }

    @Override
    public IDrugDAO getDrugDAO() {
        return new DrugDAO();
    }

    @Override
    public IInventoryDAO getInventoryDAO() {
        return new InventoryDAO();
    }

    @Override
    public IManufacturerDAO getManufacturerDAO() {
        return new ManufacturerDAO();
    }

    @Override
    public IPersonDAO getPersonDAO() {
        return new PersonDAO();
    }

    @Override
    public IPharmacyDAO getPharmacyDAO() {
        return new PharmacyDAO();
    }

    @Override
    public IPositionDAO getPositionDAO() {
        return new PositionDAO();
    }

    @Override
    public IPrescriptionDAO getPrescriptionDAO() {
        return new PrescriptionDAO();
    }

    @Override
    public IPrescriptionItemDAO getPrescriptionItemDAO() {
        return new PrescriptionItemDAO();
    }

    @Override
    public ISpecialtyDAO getSpecialtyDAO() {
        return new SpecialtyDAO();
    }

    @Override
    public IStaffDAO getStaffDAO() {
        return new StaffDAO();
    }

    @Override
    public ISupplierDAO getSupplierDAO() {
        return new SupplierDAO();
    }
}
