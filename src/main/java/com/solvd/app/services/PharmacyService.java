package com.solvd.app.services;

import com.solvd.app.dao.PharmacyDAO;
import com.solvd.app.dao.StaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class PharmacyService {
    private PharmacyDAO pharmacyDAO = new PharmacyDAO();

    public void createPharmacy(Pharmacy pharmacy) {
        pharmacyDAO.createEntity(pharmacy);
    }

    public Pharmacy getPharmacyByID(int id) {
        Pharmacy pharmacy = pharmacyDAO.getEntityByID(id);
        List<Staff> staff = new StaffDAO().getStaffByPharmacy(pharmacy);
        pharmacy.setStaff(staff);
        return pharmacy;
    }

    public void updatePharmacy(Pharmacy pharmacy) {
        pharmacyDAO.updateEntity(pharmacy);
    }

    public void deletePharmacyByID(int id) {
        pharmacyDAO.deleteEntityByID(id);
    }

    public List<Pharmacy> getAllPharmacies() {
        return pharmacyDAO.getAll();
    }
}
