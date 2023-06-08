package com.solvd.app.services;

import com.solvd.app.dao.DrugDAO;
import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class DrugService {
    private DrugDAO drugDAO = new DrugDAO();

    public void createDrug(Drug drug) {
        drugDAO.createEntity(drug);
    }

    public Drug getDrugByID(int id) {
        return drugDAO.getEntityByID(id);
    }

    public void updateDrug(Drug drug) {
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
