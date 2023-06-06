package com.solvd.app.services;

import com.solvd.app.daos.DrugCategoryDAO;
import com.solvd.app.models.DrugCategory;

import java.sql.SQLException;
import java.util.List;

public class DrugCategoryService {
    private DrugCategoryDAO drugCategoryDAO;

    public DrugCategoryService(DrugCategoryDAO drugCategoryDAO) {
        this.drugCategoryDAO = drugCategoryDAO;
    }

    public void createDrugCategory(DrugCategory drugCategory) throws SQLException {
        drugCategoryDAO.createEntity(drugCategory);
    }

    public DrugCategory getDrugCategoryByID(int id) throws SQLException {
        return drugCategoryDAO.getEntityByID(id);
    }

    public void updateDrugCategory(DrugCategory drugCategory) throws SQLException {
        drugCategoryDAO.updateEntity(drugCategory);
    }

    public void deleteDrugCategoryByID(int id) throws SQLException {
        drugCategoryDAO.deleteEntityByID(id);
    }

    public List<DrugCategory> getAllDrugCategories() throws SQLException {
        return drugCategoryDAO.getAll();
    }
}
