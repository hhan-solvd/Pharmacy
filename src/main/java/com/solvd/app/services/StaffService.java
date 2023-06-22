package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.StaffDAO;
import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.mybatis.MyBatisStaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class StaffService {

    private IStaffDAO staffDAO;

    public StaffService(DAOType type) {
        switch (type) {
            case JDBC -> this.staffDAO = new StaffDAO();
            case MYBATIS -> this.staffDAO = new MyBatisStaffDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

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
