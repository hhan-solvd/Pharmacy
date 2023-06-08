package com.solvd.app.services;

import com.solvd.app.dao.PrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;

import java.util.List;

public class PrescriptionItemService {
    private PrescriptionItemDAO prescriptionItemDAO = new PrescriptionItemDAO();

    public void createPrescriptionItem(PrescriptionItem prescriptionItem) {
        prescriptionItemDAO.createEntity(prescriptionItem);
    }

    public PrescriptionItem getPrescriptionItemByID(int id) {
        return prescriptionItemDAO.getEntityByID(id);
    }

    public void updatePrescriptionItem(PrescriptionItem prescriptionItem) {
        prescriptionItemDAO.updateEntity(prescriptionItem);
    }

    public void deletePrescriptionItemByID(int id) {
        prescriptionItemDAO.deleteEntityByID(id);
    }

    public List<PrescriptionItem> getAllPrescriptionItems() {
        return prescriptionItemDAO.getAll();
    }

    public List<PrescriptionItem> getItemsByPrescription(Prescription prescription) {
        return prescriptionItemDAO.getItemsByPrescription(prescription);
    }
}
