package com.solvd.app.services;

import com.solvd.app.daos.PharmacyDAO;
import com.solvd.app.models.Pharmacy;

import java.sql.SQLException;
import java.util.List;

public class PharmacyService {
    private PharmacyDAO pharmacyDAO;

    public PharmacyService(PharmacyDAO pharmacyDAO) {
        this.pharmacyDAO = pharmacyDAO;
    }

    public void createPharmacy(Pharmacy pharmacy) throws SQLException {
        pharmacyDAO.createEntity(pharmacy);
    }

    public Pharmacy getPharmacyByID(int id) throws SQLException {
        return pharmacyDAO.getEntityByID(id);
    }

    public void updatePharmacy(Pharmacy pharmacy) throws SQLException {
        pharmacyDAO.updateEntity(pharmacy);
    }

    public void deletePharmacyByID(int id) throws SQLException {
        pharmacyDAO.deleteEntityByID(id);
    }

    public List<Pharmacy> getAllPharmacies() throws SQLException {
        return pharmacyDAO.getAll();
    }
}
