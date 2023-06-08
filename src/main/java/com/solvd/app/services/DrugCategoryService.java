package com.solvd.app.services;

import com.solvd.app.dao.DrugCategoryDAO;
import com.solvd.app.models.DrugCategory;

import java.util.List;

public class DrugCategoryService {
    private DrugCategoryDAO drugCategoryDAO = new DrugCategoryDAO();

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
}
