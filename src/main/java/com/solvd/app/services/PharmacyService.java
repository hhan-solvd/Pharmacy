package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.PharmacyDAO;
import com.solvd.app.jdbc.StaffDAO;
import com.solvd.app.interfaces.IPharmacyDAO;
import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.mybatis.MyBatisPharmacyDAO;
import com.solvd.app.mybatis.MyBatisStaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class PharmacyService {

    private IPharmacyDAO pharmacyDAO;
    private IStaffDAO staffDAO;

    public PharmacyService(DAOType type) {
        switch (type) {
            case JDBC -> {
                this.pharmacyDAO = new PharmacyDAO();
                this.staffDAO = new StaffDAO();
            }
            case MYBATIS -> {
                this.pharmacyDAO = new MyBatisPharmacyDAO();
                this.staffDAO = new MyBatisStaffDAO();
            }
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

    public void createPharmacy(Pharmacy pharmacy) {
        pharmacyDAO.createEntity(pharmacy);
    }

    public Pharmacy getPharmacyByID(int id) {
        Pharmacy pharmacy = pharmacyDAO.getEntityByID(id);
        List<Staff> staff = staffDAO.getStaffByPharmacy(pharmacy);
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
        List<Pharmacy> pharmacyList = pharmacyDAO.getAll();

        for (Pharmacy pharmacy : pharmacyList) {
            List<Staff> staff = staffDAO.getStaffByPharmacy(pharmacy);
            pharmacy.setStaff(staff);
        }

        return pharmacyList;
    }

    public Pharmacy getPharmacyByAddress(String address) {
        Pharmacy pharmacy = pharmacyDAO.getPharmacyByAddress(address);
        List<Staff> staff = staffDAO.getStaffByPharmacy(pharmacy);
        pharmacy.setStaff(staff);
        return pharmacy;
    }
}