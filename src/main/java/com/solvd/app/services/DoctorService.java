package com.solvd.app.services;

import com.solvd.app.daos.DoctorDAO;
import com.solvd.app.models.Doctor;

import java.sql.SQLException;
import java.util.List;

public class DoctorService {
    private DoctorDAO doctorDAO;

    public DoctorService(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public void createDoctor(Doctor doctor) throws SQLException {
        doctorDAO.createEntity(doctor);
    }

    public Doctor getDoctorByID(int id) throws SQLException {
        return doctorDAO.getEntityByID(id);
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        doctorDAO.updateEntity(doctor);
    }

    public void deleteDoctorByID(int id) throws SQLException {
        doctorDAO.deleteEntityByID(id);
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorDAO.getAll();
    }
}
