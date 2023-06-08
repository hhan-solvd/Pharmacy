package com.solvd.app.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XMLValidator {

    private static final Logger LOGGER = LogManager.getLogger(XMLValidator.class);

    public static void validateXMLFile() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File xsdFile = new File("src/main/resources/schema.xsd");
            Schema schema = factory.newSchema(xsdFile);

            Validator validator = schema.newValidator();

            File xmlFile = new File("src/main/resources/data.xml");
            validator.validate(new StreamSource(xmlFile));
            LOGGER.info(xmlFile.getName() + " is valid against " + xsdFile.getName());
        } catch (Exception e) {
            LOGGER.error("Exception: " + e.getMessage());
        }
    }
}

