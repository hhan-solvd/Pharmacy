package com.solvd.app.jaxb;

import com.solvd.app.models.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUtils {

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
                    "/src/main/resources/xml/" + className + ".xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T unmarshall(String xmlFilePath, Class<T> targetType) {
        try {
            File xmlFile = new File(xmlFilePath);
            JAXBContext context = JAXBContext.newInstance(targetType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return targetType.cast(unmarshaller.unmarshal(xmlFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
