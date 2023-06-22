package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.SpecialtyDAO;
import com.solvd.app.interfaces.ISpecialtyDAO;
import com.solvd.app.mybatis.MyBatisSpecialtyDAO;
import com.solvd.app.models.Specialty;

import java.util.List;

public class SpecialtyService {

    private ISpecialtyDAO specialtyDAO;


    public SpecialtyService(DAOType type) {
        switch (type) {
            case JDBC -> this.specialtyDAO = new SpecialtyDAO();
            case MYBATIS -> this.specialtyDAO = new MyBatisSpecialtyDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

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

    public Specialty getSpecialtyByName(String name) {
        return specialtyDAO.getSpecialtyByName(name);
    }
}

