package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IPrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;

import java.util.List;

public class PrescriptionItemService {

    private IPrescriptionItemDAO prescriptionItemDAO;
    private PrescriptionService prescriptionService;
    private DrugService drugService;

    public PrescriptionItemService(DAOType type) {
        this.prescriptionItemDAO =
                (IPrescriptionItemDAO) DBFactoryGenerator.getFactory(type).getDAO("PrescriptionItem");
        this.prescriptionService = new PrescriptionService(type);
        this.drugService = new DrugService(type);
    }

    public void createPrescriptionItem(PrescriptionItem prescriptionItem) {
        if (prescriptionItem.getPrescription().getPrescriptionID() == 0) {
            prescriptionService.createPrescription(prescriptionItem.getPrescription());
        }

        if (prescriptionItem.getDrug().getDrugID() == 0) {
            drugService.createDrug(prescriptionItem.getDrug());
        }

        prescriptionItemDAO.createEntity(prescriptionItem);
    }

    public PrescriptionItem getPrescriptionItemByID(int id) {
        return prescriptionItemDAO.getEntityByID(id);
    }

    public void updatePrescriptionItem(PrescriptionItem prescriptionItem) {
        if (prescriptionItem.getPrescription().getPrescriptionID() == 0) {
            prescriptionService.createPrescription(prescriptionItem.getPrescription());
        }

        if (prescriptionItem.getDrug().getDrugID() == 0) {
            drugService.createDrug(prescriptionItem.getDrug());
        }

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
