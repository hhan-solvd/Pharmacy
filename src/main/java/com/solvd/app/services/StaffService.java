package com.solvd.app.services;

import com.solvd.app.dao.StaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class StaffService {
    private StaffDAO staffDAO = new StaffDAO();

    public void createStaff(Staff staff) {
        staffDAO.createEntity(staff);
    }

    public Staff getStaffByID(int id) {
        return staffDAO.getEntityByID(id);
    }

    public void updateStaff(Staff staff) {
        staffDAO.updateEntity(staff);
    }

    public void deleteStaffByID(int id) {
        staffDAO.deleteEntityByID(id);
    }

    public List<Staff> getAllStaff() {
        return staffDAO.getAll();
    }

    public List<Staff> getStaffByPharmacy(Pharmacy pharmacy) {
        return staffDAO.getStaffByPharmacy(pharmacy);
    }
}
