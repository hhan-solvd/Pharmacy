package com.solvd.app.services;

import com.solvd.app.dao.SpecialtyDAO;
import com.solvd.app.models.Specialty;

import java.util.List;

public class SpecialtyService {
    private SpecialtyDAO specialtyDAO = new SpecialtyDAO();

    public void createSpecialty(Specialty specialty) {
        specialtyDAO.createEntity(specialty);
    }

    public Specialty getSpecialtyByID(int id) {
        return specialtyDAO.getEntityByID(id);
    }

    public void updateSpecialty(Specialty specialty) {
        specialtyDAO.updateEntity(specialty);
    }

    public void deleteSpecialtyByID(int id) {
        specialtyDAO.deleteEntityByID(id);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyDAO.getAll();
    }
}
