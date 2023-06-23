package com.solvd.app.daofactories;

import com.solvd.app.interfaces.*;
import com.solvd.app.mybatis.*;

public class MyBatisFactory extends AbstractDAOFactory {

    @Override
    public ICustomerDAO getCustomerDAO() {
        return new MyBatisCustomerDAO();
    }

    @Override
    public IDoctorDAO getDoctorDAO() {
        return new MyBatisDoctorDAO();
    }

    @Override
    public IDrugCategoryDAO getDrugCategoryDAO() {
        return new MyBatisDrugCategoryDAO();
    }

    @Override
    public IDrugDAO getDrugDAO() {
        return new MyBatisDrugDAO();
    }

    @Override
    public IInventoryDAO getInventoryDAO() {
        return new MyBatisInventoryDAO();
    }

    @Override
    public IManufacturerDAO getManufacturerDAO() {
        return new MyBatisManufacturerDAO();
    }

    @Override
    public IPersonDAO getPersonDAO() {
        return new MyBatisPersonDAO();
    }

    @Override
    public IPharmacyDAO getPharmacyDAO() {
        return new MyBatisPharmacyDAO();
    }

    @Override
    public IPositionDAO getPositionDAO() {
        return new MyBatisPositionDAO();
    }

    @Override
    public IPrescriptionDAO getPrescriptionDAO() {
        return new MyBatisPrescriptionDAO();
    }

    @Override
    public IPrescriptionItemDAO getPrescriptionItemDAO() {
        return new MyBatisPrescriptionItemDAO();
    }

    @Override
    public ISpecialtyDAO getSpecialtyDAO() {
        return new MyBatisSpecialtyDAO();
    }

    @Override
    public IStaffDAO getStaffDAO() {
        return new MyBatisStaffDAO();
    }

    @Override
    public ISupplierDAO getSupplierDAO() {
        return new MyBatisSupplierDAO();
    }
}
