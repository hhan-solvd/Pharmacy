package com.solvd.app.services;

import com.solvd.app.daos.PrescriptionDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.models.Prescription;

import java.sql.SQLException;
import java.util.List;

public class PrescriptionService {
    private PrescriptionDAO prescriptionDAO;

    public PrescriptionService(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void createPrescription(Prescription prescription) throws SQLException {
        prescriptionDAO.createEntity(prescription);
    }

    public Prescription getPrescriptionByID(int id) throws SQLException {
        return prescriptionDAO.getEntityByID(id);
    }

    public void updatePrescription(Prescription prescription) throws SQLException {
        prescriptionDAO.updateEntity(prescription);
    }

    public void deletePrescriptionByID(int id) throws SQLException {
        prescriptionDAO.deleteEntityByID(id);
    }

    public List<Prescription> getAllPrescriptions() throws SQLException {
        return prescriptionDAO.getAll();
    }

    public List<Prescription> getPrescriptionsByDoctors(Doctor doctor) throws SQLException {
        return prescriptionDAO.getPrescriptionsByDoctor(doctor);
    }
}
