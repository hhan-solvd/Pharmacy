package com.solvd.app.services;

import com.solvd.app.daos.StaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.sql.SQLException;
import java.util.List;

public class StaffService {
    private StaffDAO staffDAO;

    public StaffService(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public void createStaff(Staff staff) throws SQLException {
        staffDAO.createEntity(staff);
    }

    public Staff getStaffByID(int id) throws SQLException {
        return staffDAO.getEntityByID(id);
    }

    public void updateStaff(Staff staff) throws SQLException {
        staffDAO.updateEntity(staff);
    }

    public void deleteStaffByID(int id) throws SQLException {
        staffDAO.deleteEntityByID(id);
    }

    public List<Staff> getAllStaff() throws SQLException {
        return staffDAO.getAll();
    }

    public List<Staff> getStaffByPharmacy(Pharmacy pharmacy) throws SQLException {
        return staffDAO.getStaffByPharmacy(pharmacy);
    }
}
