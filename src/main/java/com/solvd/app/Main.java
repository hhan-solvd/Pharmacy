package com.solvd.app;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jaxb.JAXBUtils;
import com.solvd.app.json.JSONUtils;
import com.solvd.app.models.*;
import com.solvd.app.services.*;
import com.solvd.app.utils.DOMParser;
import com.solvd.app.utils.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("------------------Usage of JDBC---------------------");
        CustomerService customerService = new CustomerService(DAOType.JDBC);
        DoctorService doctorService = new DoctorService(DAOType.JDBC);
        PrescriptionService prescriptionService = new PrescriptionService(DAOType.JDBC);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Prescription prescription = new Prescription(sqlDate, doctorService.getDoctorByID(12),
                customerService.getCustomerByID(7));
        prescriptionService.createPrescription(prescription);
        LOGGER.info(prescriptionService.getPrescriptionByID(prescription.getPrescriptionID()));
        prescription.setCustomer(customerService.getCustomerByID(9));
        prescriptionService.updatePrescription(prescription);
        LOGGER.info(prescriptionService.getAllPrescriptions());
        LOGGER.info(prescriptionService.getPrescriptionsByDoctors(doctorService.getDoctorByID(12)));
        prescriptionService.deletePrescriptionByID(prescription.getPrescriptionID());

        LOGGER.info("------------------Usage of XML validator and parser---------------------");
        XMLValidator.validateXMLFile("src/main/resources/xml/data.xml",
                "src/main/resources/xml/schema.xsd");
        DOMParser.parseXMLFile("src/main/resources/xml/data.xml");

        LOGGER.info("------------------Usage of JAXB---------------------");
        PharmacyService pharmacyService = new PharmacyService(DAOType.JDBC);
        Pharmacy pharmacy = pharmacyService.getPharmacyByID(9);
        JAXBUtils.marshall(pharmacy, "src/main/resources/xml/");
        LOGGER.info(JAXBUtils.unmarshall("src/main/resources/xml/Pharmacy.xml", Pharmacy.class));

        LOGGER.info("------------------Usage of JSON---------------------");
        Prescription prescription1 = prescriptionService.getPrescriptionByID(12);
        JSONUtils.writeJSON(prescription1, "src/main/resources/json/");
        LOGGER.info(JSONUtils.readJSON("src/main/resources/json/Prescription.json", Prescription.class));

        LOGGER.info("------------------Usage of MyBatis---------------------");
        PersonService personService = new PersonService(DAOType.MYBATIS);
        SpecialtyService specialtyService = new SpecialtyService(DAOType.MYBATIS);
        DoctorService doctorService1 = new DoctorService(DAOType.MYBATIS);
        Doctor doctor1 = new Doctor(personService.getPersonByID(28), specialtyService.getSpecialtyByID(17),
                20);
        doctorService1.createDoctor(doctor1);
        Doctor doctor2 = doctorService1.getDoctorByID(12);
        doctor2.setYearsOfExperience(25);
        doctorService1.updateDoctor(doctor2);
        LOGGER.info(doctorService1.getDoctorByID(12));
        LOGGER.info(doctorService1.getDoctorsBySpecialty("Radiology"));
        LOGGER.info(doctorService1.getAllDoctors());
        doctorService1.deleteDoctorByID(66);
    }
}
