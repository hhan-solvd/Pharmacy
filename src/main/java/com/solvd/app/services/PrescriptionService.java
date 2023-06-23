package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IPrescriptionDAO;
import com.solvd.app.jdbc.PrescriptionItemDAO;
import com.solvd.app.models.*;

import java.util.List;

public class PrescriptionService {

    private IPrescriptionDAO prescriptionDAO;
    private DoctorService doctorService;
    private CustomerService customerService;
    private PrescriptionItemDAO prescriptionItemDAO;

    public PrescriptionService(DAOType type) {
        this.prescriptionDAO = (IPrescriptionDAO) DBFactoryGenerator.getFactory(type).getDAO("Prescription");
        this.prescriptionItemDAO =
                (PrescriptionItemDAO) DBFactoryGenerator.getFactory(type).getDAO("PrescriptionItem");
        this.doctorService = new DoctorService(type);
        this.customerService = new CustomerService(type);
    }

    public void createPrescription(Prescription prescription) {
        if (prescription.getDoctor().getDoctorID() == 0) {
            doctorService.createDoctor(prescription.getDoctor());
        }

        if (prescription.getCustomer().getCustomerID() == 0) {
            customerService.createCustomer(prescription.getCustomer());
        }

        prescriptionDAO.createEntity(prescription);
    }

    public Prescription getPrescriptionByID(int id) {
        Prescription prescription = prescriptionDAO.getEntityByID(id);
        List<PrescriptionItem> prescriptionItems = prescriptionItemDAO.getItemsByPrescription(prescription);
        prescription.setPrescriptionItems(prescriptionItems);
        return prescription;
    }

    public void updatePrescription(Prescription prescription) {
        if (prescription.getDoctor().getDoctorID() == 0) {
            doctorService.createDoctor(prescription.getDoctor());
        }

        if (prescription.getCustomer().getCustomerID() == 0) {
            customerService.createCustomer(prescription.getCustomer());
        }

        prescriptionDAO.updateEntity(prescription);
    }

    public void deletePrescriptionByID(int id) {
        prescriptionDAO.deleteEntityByID(id);
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptionList = prescriptionDAO.getAll();

        for (Prescription prescription : prescriptionList) {
            List<PrescriptionItem> prescriptionItems = prescriptionItemDAO.getItemsByPrescription(prescription);
            prescription.setPrescriptionItems(prescriptionItems);
        }

        return prescriptionList;
    }

    public List<Prescription> getPrescriptionsByDoctors(Doctor doctor) {
        List<Prescription> prescriptionList = prescriptionDAO.getPrescriptionsByDoctor(doctor);

        for (Prescription prescription : prescriptionList) {
            List<PrescriptionItem> prescriptionItems = prescriptionItemDAO.getItemsByPrescription(prescription);
            prescription.setPrescriptionItems(prescriptionItems);
        }

        return prescriptionList;
    }
}
