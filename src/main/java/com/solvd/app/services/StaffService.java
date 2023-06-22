package com.solvd.app.services;

import com.solvd.app.jdbc.StaffDAO;
import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.mybatis.MyBatisStaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class StaffService {

    private IStaffDAO staffDAO;

    public StaffService() {
        this.staffDAO = new StaffDAO();
    }

    public StaffService(MyBatisStaffDAO myBatisStaffDAO) {
        this.staffDAO = myBatisStaffDAO;
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
