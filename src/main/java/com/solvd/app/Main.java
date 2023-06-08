package com.solvd.app;

import com.solvd.app.models.*;
import com.solvd.app.services.*;
import com.solvd.app.utils.DOMParser;
import com.solvd.app.utils.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("------------------Usage of PersonService---------------------");
        PersonService personService = new PersonService();
        Person person = new Person("Jack", "4768 Main street", 345543,
                "test3@example.com", "Male");
        personService.createPerson(person);
        LOGGER.info(personService.getPersonByID(person.getPersonID()));
        person.setAddress("1235 Main Street");
        LOGGER.info(personService.getPersonByNameAndPhoneNumber("Jack", 345543));
        personService.updatePerson(person);
        LOGGER.info(personService.getAllPersons());
        personService.deletePersonByID(person.getPersonID());

        LOGGER.info("------------------Usage of CustomerService---------------------");
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer(
                personService.getPersonByNameAndPhoneNumber("Jack", 345543));
        customerService.createCustomer(customer);
        LOGGER.info(customerService.getCustomerByID(customer.getCustomerID()));
        customer.setPerson(personService.getPersonByID(28));
        customerService.updateCustomer(customer);
        LOGGER.info(customerService.getAllCustomers());
        customerService.deleteCustomerByID(customer.getCustomerID());

        LOGGER.info("------------------Usage of SpecialtyService---------------------");
        SpecialtyService specialtyService = new SpecialtyService();
        Specialty specialty = new Specialty("Radiology");
        specialtyService.createSpecialty(specialty);
        LOGGER.info(specialtyService.getSpecialtyByID(specialty.getSpecialtyID()));
        specialty.setName("Cardiology");
        specialtyService.updateSpecialty(specialty);
        LOGGER.info(specialtyService.getAllSpecialties());
        specialtyService.deleteSpecialtyByID(specialty.getSpecialtyID());

        LOGGER.info("------------------Usage of DoctorService---------------------");
        DoctorService doctorService = new DoctorService();
        Doctor doctor = new Doctor(personService.getPersonByID(28), specialtyService.getSpecialtyByID(17),
                15);
        doctorService.createDoctor(doctor);
        LOGGER.info(doctorService.getDoctorByID(doctor.getDoctorID()));
        doctor.setYearsOfExperience(20);
        doctorService.updateDoctor(doctor);
        LOGGER.info(doctorService.getAllDoctors());
        doctorService.deleteDoctorByID(doctor.getDoctorID());

        LOGGER.info("------------------Usage of PositionService---------------------");
        PositionService positionService = new PositionService();
        Position position = new Position("Cashier", 50000);
        positionService.createPosition(position);
        LOGGER.info(positionService.getPositionByID(position.getPositionID()));
        position.setSalary(55000);
        positionService.updatePosition(position);
        LOGGER.info(positionService.getAllPositions());
        positionService.deletePositionByID(position.getPositionID());

        LOGGER.info("------------------Usage of PharmacyService---------------------");
        PharmacyService pharmacyService = new PharmacyService();
        Pharmacy pharmacy = new Pharmacy("CVS", "123 Main Street", 123409);
        pharmacyService.createPharmacy(pharmacy);
        LOGGER.info(pharmacyService.getPharmacyByID(pharmacy.getPharmacyID()));
        pharmacy.setAddress("467 Main Street");
        pharmacyService.updatePharmacy(pharmacy);
        LOGGER.info(pharmacyService.getAllPharmacies());
        pharmacyService.deletePharmacyByID(pharmacy.getPharmacyID());

        LOGGER.info("------------------Usage of StaffService---------------------");
        StaffService staffService = new StaffService();
        Staff staff = new Staff(personService.getPersonByID(31), pharmacyService.getPharmacyByID(9),
                positionService.getPositionByID(15));
        staffService.createStaff(staff);
        LOGGER.info(staffService.getStaffByID(staff.getStaffID()));
        staff.setPosition(positionService.getPositionByID(17));
        staffService.updateStaff(staff);
        LOGGER.info(staffService.getAllStaff());
        LOGGER.info(staffService.getStaffByPharmacy(pharmacyService.getPharmacyByID(9)));
        staffService.deleteStaffByID(staff.getStaffID());

        LOGGER.info("------------------Usage of SupplierService---------------------");
        SupplierService supplierService = new SupplierService();
        Supplier supplier = new Supplier("ABC", "876 Main Road", 675432);
        supplierService.createSupplier(supplier);
        LOGGER.info(supplierService.getSupplierByID(supplier.getSupplierID()));
        supplier.setPhoneNumber(988967);
        supplierService.updateSupplier(supplier);
        LOGGER.info(supplierService.getAllSuppliers());
        supplierService.deleteSupplierByID(supplier.getSupplierID());

        LOGGER.info("------------------Usage of ManufacturerService---------------------");
        ManufacturerService manufacturerService = new ManufacturerService();
        Manufacturer manufacturer = new Manufacturer("Pfizer");
        manufacturerService.createManufacturer(manufacturer);
        LOGGER.info(manufacturerService.getManufacturerByID(manufacturer.getManufacturerID()));
        manufacturer.setName("Johnson & Johnson");
        manufacturerService.updateManufacturer(manufacturer);
        LOGGER.info(manufacturerService.getAllManufacturers());
        manufacturerService.deleteManufacturerByID(manufacturer.getManufacturerID());

        LOGGER.info("------------------Usage of DrugCategoryService---------------------");
        DrugCategoryService drugCategoryService = new DrugCategoryService();
        DrugCategory drugCategory = new DrugCategory("Antibiotic");
        drugCategoryService.createDrugCategory(drugCategory);
        LOGGER.info(drugCategoryService.getDrugCategoryByID(drugCategory.getCategoryID()));
        drugCategory.setName("Antacids");
        drugCategoryService.updateDrugCategory(drugCategory);
        LOGGER.info(drugCategoryService.getAllDrugCategories());
        drugCategoryService.deleteDrugCategoryByID(drugCategory.getCategoryID());

        LOGGER.info("------------------Usage of DrugService---------------------");
        DrugService drugService = new DrugService();
        Drug drug = new Drug("Penicillin", supplierService.getSupplierByID(11),
                manufacturerService.getManufacturerByID(16), drugCategoryService.getDrugCategoryByID(9), 18);
        drugService.createDrug(drug);
        LOGGER.info(drugService.getDrugByID(drug.getDrugID()));
        drug.setPrice(16);
        drugService.updateDrug(drug);
        LOGGER.info(drugService.getAllDrugs());
        LOGGER.info(drugService.getDrugsByManufacturer(manufacturerService.getManufacturerByID(16)));
        drugService.deleteDrugByID(drug.getDrugID());

        LOGGER.info("------------------Usage of InventoryService---------------------");
        InventoryService inventoryService = new InventoryService();
        Inventory inventory = new Inventory(100, drugService.getDrugByID(7),
                pharmacyService.getPharmacyByID(9));
        inventoryService.createInventory(inventory);
        LOGGER.info(inventoryService.getInventoryByID(inventory.getInventoryID()));
        inventory.setQuantity(200);
        inventoryService.updateInventory(inventory);
        LOGGER.info(inventoryService.getAllInventories());
        inventoryService.deleteInventoryByID(inventory.getInventoryID());

        LOGGER.info("------------------Usage of PrescriptionService---------------------");
        PrescriptionService prescriptionService = new PrescriptionService();
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

        LOGGER.info("------------------Usage of PrescriptionItemService---------------------");
        PrescriptionItemService prescriptionItemService = new PrescriptionItemService();
        PrescriptionItem prescriptionItem = new PrescriptionItem(15,
                prescriptionService.getPrescriptionByID(13), drugService.getDrugByID(7));
        prescriptionItemService.createPrescriptionItem(prescriptionItem);
        LOGGER.info(prescriptionItemService.getPrescriptionItemByID(prescriptionItem.getPrescriptionItemID()));
        prescriptionItem.setQuantityPrescribed(20);
        prescriptionItemService.updatePrescriptionItem(prescriptionItem);
        LOGGER.info(prescriptionItemService.getAllPrescriptionItems());
        LOGGER.info(prescriptionItemService.getItemsByPrescription(prescriptionService.getPrescriptionByID(13)));
        prescriptionItemService.deletePrescriptionItemByID(prescriptionItem.getPrescriptionItemID());

        LOGGER.info("------------------Usage of XML validator and parser---------------------");
        XMLValidator.validateXMLFile("src/main/resources/data.xml",
                "src/main/resources/schema.xsd");
        DOMParser.parseXMLFile("src/main/resources/data.xml");
    }
}
