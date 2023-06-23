package com.solvd.app.daofactories;

import com.solvd.app.interfaces.*;

public abstract class AbstractDAOFactory {

    public abstract ICustomerDAO getCustomerDAO();

    public abstract IDoctorDAO getDoctorDAO();

    public abstract IDrugCategoryDAO getDrugCategoryDAO();

    public abstract IDrugDAO getDrugDAO();

    public abstract IInventoryDAO getInventoryDAO();

    public abstract IManufacturerDAO getManufacturerDAO();

    public abstract IPersonDAO getPersonDAO();

    public abstract IPharmacyDAO getPharmacyDAO();

    public abstract IPositionDAO getPositionDAO();

    public abstract IPrescriptionDAO getPrescriptionDAO();

    public abstract IPrescriptionItemDAO getPrescriptionItemDAO();

    public abstract ISpecialtyDAO getSpecialtyDAO();

    public abstract IStaffDAO getStaffDAO();

    public abstract ISupplierDAO getSupplierDAO();
}

