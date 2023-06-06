package com.solvd.app.services;

import com.solvd.app.daos.PrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;

import java.sql.SQLException;
import java.util.List;

public class PrescriptionItemService {
    private PrescriptionItemDAO prescriptionItemDAO;

    public PrescriptionItemService(PrescriptionItemDAO prescriptionItemDAO) {
        this.prescriptionItemDAO = prescriptionItemDAO;
    }

    public void createPrescriptionItem(PrescriptionItem prescriptionItem) throws SQLException {
        prescriptionItemDAO.createEntity(prescriptionItem);
    }

    public PrescriptionItem getPrescriptionItemByID(int id) throws SQLException {
        return prescriptionItemDAO.getEntityByID(id);
    }

    public void updatePrescriptionItem(PrescriptionItem prescriptionItem) throws SQLException {
        prescriptionItemDAO.updateEntity(prescriptionItem);
    }

    public void deletePrescriptionItemByID(int id) throws SQLException {
        prescriptionItemDAO.deleteEntityByID(id);
    }

    public List<PrescriptionItem> getAllPrescriptionItems() throws SQLException {
        return prescriptionItemDAO.getAll();
    }

    public List<PrescriptionItem> getItemsByPrescription(Prescription prescription) throws SQLException {
        return prescriptionItemDAO.getItemsByPrescription(prescription);
    }
}
