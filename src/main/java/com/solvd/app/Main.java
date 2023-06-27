package com.solvd.app;

import com.solvd.app.decorators.VIPCustomer;
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
        Prescription prescription = new Prescription.Builder()
                .withPrescriptionDate(sqlDate)
                .withDoctor(doctorService.getDoctorByID(12))
                .withCustomer(customerService.getCustomerByID(7))
                .build();
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
        Doctor doctor1 = new Doctor.Builder()
                .withPerson(personService.getPersonByID(28))
                .withSpecialty(specialtyService.getSpecialtyByID(17))
                .withYearsOfExperience(20)
                .build();
        doctorService1.createDoctor(doctor1);
        Doctor doctor2 = doctorService1.getDoctorByID(12);
        doctor2.setYearsOfExperience(25);
        doctorService1.updateDoctor(doctor2);
        LOGGER.info(doctorService1.getDoctorByID(12));
        LOGGER.info(doctorService1.getDoctorsBySpecialty("Radiology"));
        LOGGER.info(doctorService1.getAllDoctors());
        doctorService1.deleteDoctorByID(66);

        LOGGER.info("------------------Usage of Design Patterns---------------------");
        DrugService drugService = new DrugService(DAOType.JDBC);
        DrugCategoryService drugCategoryService = new DrugCategoryService(DAOType.JDBC);
        ManufacturerService manufacturerService = new ManufacturerService(DAOType.JDBC);
        SupplierService supplierService = new SupplierService(DAOType.JDBC);
        Drug drug = new Drug.Builder()
                .withName("Penicillin")
                .withSupplier(supplierService.getSupplierByID(11))
                .withManufacturer(manufacturerService.getManufacturerByID(16))
                .withDrugCategory(drugCategoryService.getDrugCategoryByID(9))
                .withPrice(16.5)
                .build();
        drugService.createDrug(drug);

        Customer customer = new Customer.Builder()
                .withPerson(personService.getPersonByID(31))
                .build();
        VIPCustomer vipCustomer = new VIPCustomer.Builder()
                .withCustomer(customer)
                .withDiscountPercentage(20)
                .build();
        customerService.createCustomer(vipCustomer);

        VIPCustomer vipCustomer1 = new VIPCustomer.Builder()
                .withCustomer(customerService.getCustomerByID(7))
                .withDiscountPercentage(15)
                .build();
        vipCustomer1.saveToFile("VIPCustomer");
    }
}
