package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IDrugDAO;
import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class DrugService {

    private IDrugDAO drugDAO;
    private SupplierService supplierService;
    private ManufacturerService manufacturerService;
    private DrugCategoryService drugCategoryService;

    public DrugService(DAOType type) {
        this.drugDAO = (IDrugDAO) DBFactoryGenerator.getFactory(type).getDAO("Drug");
        this.supplierService = new SupplierService(type);
        this.manufacturerService = new ManufacturerService(type);
        this.drugCategoryService = new DrugCategoryService(type);
    }

    public void createDrug(Drug drug) {
        if (drug.getSupplier().getSupplierID() == 0) {
            supplierService.createSupplier(drug.getSupplier());
        }

        if (drug.getManufacturer().getManufacturerID() == 0) {
            manufacturerService.createManufacturer(drug.getManufacturer());
        }

        if (drug.getDrugCategory().getCategoryID() == 0) {
            drugCategoryService.createDrugCategory(drug.getDrugCategory());
        }

        drugDAO.createEntity(drug);
    }

    public Drug getDrugByID(int id) {
        return drugDAO.getEntityByID(id);
    }

    public void updateDrug(Drug drug) {
        if (drug.getSupplier().getSupplierID() == 0) {
            supplierService.createSupplier(drug.getSupplier());
        }

        if (drug.getManufacturer().getManufacturerID() == 0) {
            manufacturerService.createManufacturer(drug.getManufacturer());
        }

        if (drug.getDrugCategory().getCategoryID() == 0) {
            drugCategoryService.createDrugCategory(drug.getDrugCategory());
        }

        drugDAO.updateEntity(drug);
    }

    public void deleteDrugByID(int id) {
        drugDAO.deleteEntityByID(id);
    }

    public List<Drug> getAllDrugs() {
        return drugDAO.getAll();
    }

    public List<Drug> getDrugsByManufacturer(Manufacturer manufacturer) {
        return drugDAO.getDrugsByManufacturer(manufacturer);
    }
}
