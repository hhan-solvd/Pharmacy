package com.solvd.app.services;

import com.solvd.app.dao.DoctorDAO;
import com.solvd.app.models.Doctor;

import java.sql.SQLException;
import java.util.List;

public class DoctorService {
    private DoctorDAO doctorDAO = new DoctorDAO();

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
}
