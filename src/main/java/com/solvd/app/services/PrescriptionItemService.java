package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IPrescriptionItemDAO;
import com.solvd.app.jdbc.PrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;
import com.solvd.app.mybatis.MyBatisPrescriptionItemDAO;

import java.util.List;

public class PrescriptionItemService {

    private IPrescriptionItemDAO prescriptionItemDAO;

    public PrescriptionItemService(DAOType type) {
        switch (type) {
            case JDBC -> this.prescriptionItemDAO = new PrescriptionItemDAO();
            case MYBATIS -> this.prescriptionItemDAO = new MyBatisPrescriptionItemDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

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
