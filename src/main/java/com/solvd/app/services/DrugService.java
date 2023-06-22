package com.solvd.app.services;

import com.solvd.app.jdbc.DrugDAO;
import com.solvd.app.interfaces.IDrugDAO;
import com.solvd.app.mybatis.MyBatisDrugDAO;
import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class DrugService {

    private IDrugDAO drugDAO;

    public DrugService() {
        this.drugDAO = new DrugDAO();
    }

    public DrugService(MyBatisDrugDAO myBatisDrugDAO) {
        this.drugDAO = myBatisDrugDAO;
    }

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
