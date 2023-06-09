package com.solvd.app.utils;

import com.solvd.app.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.util.function.Function;

public class DOMParser {

    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void parseXMLFile(String xmlFilePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFilePath);

            parseElements(doc, "pharmacy", DOMParser::parsePharmacy);
            parseElements(doc, "person", DOMParser::parsePerson);
            parseElements(doc, "position", DOMParser::parsePosition);
            parseElements(doc, "staff", DOMParser::parseStaff);
            parseElements(doc, "customer", DOMParser::parseCustomer);
        } catch (Exception e) {
            LOGGER.error("Error while parsing XML file", e);
        }
    }

    private static void parseElements(Document doc, String tagName, Function<Element, Object> parserFunction) {
        NodeList nodes = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Object parsedObject = parserFunction.apply(element);
            LOGGER.info(parsedObject);
        }
    }

    public static Person parsePerson(Element element) {
        Person person = new Person();
        person.setPersonID(Integer.parseInt(element.getAttribute("person_id")));
        person.setName(element.getElementsByTagName("name").item(0).getTextContent());
        person.setAddress(element.getElementsByTagName("address").item(0).getTextContent());
        person.setPhoneNumber(Integer.parseInt(
                element.getElementsByTagName("phone_number").item(0).getTextContent()));
        person.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
        person.setGender(element.getElementsByTagName("gender").item(0).getTextContent());

        return person;
    }

    public static Pharmacy parsePharmacy(Element element) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPharmacyID(Integer.parseInt(element.getAttribute("pharmacy_id")));
        pharmacy.setName(element.getElementsByTagName("name").item(0).getTextContent());
        pharmacy.setAddress(element.getElementsByTagName("address").item(0).getTextContent());
        pharmacy.setPhoneNumber(Integer.parseInt(
                element.getElementsByTagName("phone_number").item(0).getTextContent()));

        return pharmacy;
    }

    public static Position parsePosition(Element element) {
        Position position = new Position();
        position.setPositionID(Integer.parseInt(element.getAttribute("position_id")));
        position.setName(element.getElementsByTagName("name").item(0).getTextContent());
        position.setSalary(Double.parseDouble(
                element.getElementsByTagName("salary").item(0).getTextContent()));

        return position;
    }

    public static Staff parseStaff(Element element) {
        Staff staff = new Staff();
        staff.setStaffID(Integer.parseInt(element.getAttribute("staff_id")));
        staff.setPerson(parsePerson((Element) element.getElementsByTagName("person").item(0)));
        staff.setPharmacy(parsePharmacy((Element) element.getElementsByTagName("pharmacy").item(0)));
        staff.setPosition(parsePosition((Element) element.getElementsByTagName("position").item(0)));

        return staff;
    }

    public static Customer parseCustomer(Element element) {
        Customer customer = new Customer();
        customer.setCustomerID(Integer.parseInt(element.getAttribute("customer_id")));
        customer.setPerson(parsePerson((Element) element.getElementsByTagName("person").item(0)));

        return customer;
    }
}
