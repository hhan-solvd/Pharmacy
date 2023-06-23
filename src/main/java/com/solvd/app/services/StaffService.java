package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public class StaffService {

    private IStaffDAO staffDAO;
    private PersonService personService;
    private PharmacyService pharmacyService;
    private PositionService positionService;

    public StaffService(DAOType type) {
        this.staffDAO = DBFactoryGenerator.getFactory(type).getStaffDAO();
        this.personService = new PersonService(type);
        this.pharmacyService = new PharmacyService(type);
        this.positionService = new PositionService(type);
    }

    public void createStaff(Staff staff) {
        System.out.println(staff.getPerson().getPersonID());
        if (staff.getPerson().getPersonID() == 0) {
            personService.createPerson(staff.getPerson());
        }

        if (staff.getPharmacy().getPharmacyID() == 0) {
            pharmacyService.createPharmacy(staff.getPharmacy());
        }

        if (staff.getPosition().getPositionID() == 0) {
            positionService.createPosition(staff.getPosition());
        }

        staffDAO.createEntity(staff);
    }

    public Staff getStaffByID(int id) {
        return staffDAO.getEntityByID(id);
    }

    public void updateStaff(Staff staff) {
        if (staff.getPerson().getPersonID() == 0) {
            personService.createPerson(staff.getPerson());
        }

        if (staff.getPharmacy().getPharmacyID() == 0) {
            pharmacyService.createPharmacy(staff.getPharmacy());
        }

        if (staff.getPosition().getPositionID() == 0) {
            positionService.createPosition(staff.getPosition());
        }

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
