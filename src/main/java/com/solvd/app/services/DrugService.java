package com.solvd.app.services;

import com.solvd.app.daos.DrugDAO;
import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;

import java.sql.SQLException;
import java.util.List;

public class DrugService {
    private DrugDAO drugDAO;

    public DrugService(DrugDAO drugDAO) {
        this.drugDAO = drugDAO;
    }

    public void createDrug(Drug drug) throws SQLException {
        drugDAO.createEntity(drug);
    }

    public Drug getDrugByID(int id) throws SQLException {
        return drugDAO.getEntityByID(id);
    }

    public void updateDrug(Drug drug) throws SQLException {
        drugDAO.updateEntity(drug);
    }

    public void deleteDrugByID(int id) throws SQLException {
        drugDAO.deleteEntityByID(id);
    }

    public List<Drug> getAllDrugs() throws SQLException {
        return drugDAO.getAll();
    }

    public List<Drug> getDrugsByManufacturer(Manufacturer manufacturer) throws SQLException {
        return drugDAO.getDrugsByManufacturer(manufacturer);
    }
}
