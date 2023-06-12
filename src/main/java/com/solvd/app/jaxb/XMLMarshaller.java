package com.solvd.app.jaxb;

import com.solvd.app.models.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XMLMarshaller {

    public static <T> void marshall(T parameter) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class, Doctor.class, Drug.class,
                    DrugCategory.class, Inventory.class, Manufacturer.class, Person.class, Pharmacy.class,
                    Position.class, Prescription.class, PrescriptionItem.class, Specialty.class,
                    Staff.class, Supplier.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String className = parameter.getClass().getSimpleName();
            marshaller.marshal(parameter, new File(System.getProperty("user.dir") +
                    "/src/main/resources/" + className + ".xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
