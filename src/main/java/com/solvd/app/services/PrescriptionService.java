package com.solvd.app.services;

import com.solvd.app.dao.PrescriptionDAO;
import com.solvd.app.dao.PrescriptionItemDAO;
import com.solvd.app.dao.StaffDAO;
import com.solvd.app.models.*;

import java.util.List;

public class PrescriptionService {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    public void createPrescription(Prescription prescription) {
        prescriptionDAO.createEntity(prescription);
    }

    public Prescription getPrescriptionByID(int id) {
        Prescription prescription = prescriptionDAO.getEntityByID(id);
        List<PrescriptionItem> prescriptionItems = new PrescriptionItemDAO().getItemsByPrescription(prescription);
        prescription.setPrescriptionItems(prescriptionItems);
        return prescription;
    }

    public void updatePrescription(Prescription prescription) {
        prescriptionDAO.updateEntity(prescription);
    }

    public void deletePrescriptionByID(int id) {
        prescriptionDAO.deleteEntityByID(id);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAll();
    }

    public List<Prescription> getPrescriptionsByDoctors(Doctor doctor) {
        return prescriptionDAO.getPrescriptionsByDoctor(doctor);
    }
}
