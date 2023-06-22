package com.solvd.app.services;

import com.solvd.app.jdbc.PrescriptionDAO;
import com.solvd.app.jdbc.PrescriptionItemDAO;
import com.solvd.app.interfaces.IPrescriptionDAO;
import com.solvd.app.interfaces.IPrescriptionItemDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;
import com.solvd.app.mybatis.MyBatisPrescriptionDAO;
import com.solvd.app.mybatis.MyBatisPrescriptionItemDAO;

import java.util.List;

public class PrescriptionService {

    private IPrescriptionDAO prescriptionDAO;
    private IPrescriptionItemDAO prescriptionItemDAO;

    public PrescriptionService() {
        this.prescriptionDAO = new PrescriptionDAO();
        this.prescriptionItemDAO = new PrescriptionItemDAO();
    }

    public PrescriptionService(MyBatisPrescriptionDAO myBatisPrescriptionDAO,
                               MyBatisPrescriptionItemDAO myBatisPrescriptionItemDAO) {
        this.prescriptionDAO = myBatisPrescriptionDAO;
        this.prescriptionItemDAO = myBatisPrescriptionItemDAO;
    }

    public void createPrescription(Prescription prescription) {
        prescriptionDAO.createEntity(prescription);
    }

    public Prescription getPrescriptionByID(int id) {
        Prescription prescription = prescriptionDAO.getEntityByID(id);
        List<PrescriptionItem> prescriptionItems = prescriptionItemDAO.getItemsByPrescription(prescription);
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

