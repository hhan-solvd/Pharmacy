package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IDrugCategoryDAO;
import com.solvd.app.models.DrugCategory;

import java.util.List;

public class DrugCategoryService {

    private IDrugCategoryDAO drugCategoryDAO;

    public DrugCategoryService(DAOType type) {
        this.drugCategoryDAO = (IDrugCategoryDAO) DBFactoryGenerator.getFactory(type).getDAO("DrugCategory");
    }

    public void createDrugCategory(DrugCategory drugCategory) {
        drugCategoryDAO.createEntity(drugCategory);
    }

    public DrugCategory getDrugCategoryByID(int id) {
        return drugCategoryDAO.getEntityByID(id);
    }

    public void updateDrugCategory(DrugCategory drugCategory) {
        drugCategoryDAO.updateEntity(drugCategory);
    }

    public void deleteDrugCategoryByID(int id) {
        drugCategoryDAO.deleteEntityByID(id);
    }

    public List<DrugCategory> getAllDrugCategories() {
        return drugCategoryDAO.getAll();
    }

    public DrugCategory getDrugCategoryByName(String name) {
        return drugCategoryDAO.getDrugCategoryByName(name);
    }
}