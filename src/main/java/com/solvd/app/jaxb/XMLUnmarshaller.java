package com.solvd.app.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLUnmarshaller {
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

