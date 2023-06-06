package com.solvd.app.services;

import com.solvd.app.daos.SpecialtyDAO;
import com.solvd.app.models.Specialty;

import java.sql.SQLException;
import java.util.List;

public class SpecialtyService {
    private SpecialtyDAO specialtyDAO;

    public SpecialtyService(SpecialtyDAO specialtyDAO) {
        this.specialtyDAO = specialtyDAO;
    }

    public void createSpecialty(Specialty specialty) throws SQLException {
        specialtyDAO.createEntity(specialty);
    }

    public Specialty getSpecialtyByID(int id) throws SQLException {
        return specialtyDAO.getEntityByID(id);
    }

    public void updateSpecialty(Specialty specialty) throws SQLException {
        specialtyDAO.updateEntity(specialty);
    }

    public void deleteSpecialtyByID(int id) throws SQLException {
        specialtyDAO.deleteEntityByID(id);
    }

    public List<Specialty> getAllSpecialties() throws SQLException {
        return specialtyDAO.getAll();
    }
}
