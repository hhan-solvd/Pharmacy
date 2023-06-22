package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IDoctorDAO;
import com.solvd.app.jdbc.DoctorDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.mybatis.MyBatisDoctorDAO;

import java.util.List;

public class DoctorService {

    private IDoctorDAO doctorDAO;

    public DoctorService(DAOType type) {
        switch (type) {
            case JDBC -> this.doctorDAO = new DoctorDAO();
            case MYBATIS -> this.doctorDAO = new MyBatisDoctorDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

    public void createDoctor(Doctor doctor) {
        doctorDAO.createEntity(doctor);
    }

    public Doctor getDoctorByID(int id) {
        return doctorDAO.getEntityByID(id);
    }

    public void updateDoctor(Doctor doctor) {
        doctorDAO.updateEntity(doctor);
    }

    public void deleteDoctorByID(int id) {
        doctorDAO.deleteEntityByID(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAll();
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorDAO.getDoctorsBySpecialty(specialty);
    }
}

