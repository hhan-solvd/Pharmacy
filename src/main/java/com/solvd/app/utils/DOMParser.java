package com.solvd.app.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Position;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void parseXMLFile(String xmlFilePath) {

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            LOGGER.info("Root element: " + doc.getDocumentElement().getNodeName());

            List<Pharmacy> pharmacies = parsePharmacies(doc);
            List<Position> positions = parsePositions(doc);

            LOGGER.info("Pharmacies:");
            pharmacies.forEach(pharmacy -> LOGGER.info(pharmacy.toString()));

            LOGGER.info("Positions:");
            positions.forEach(position -> LOGGER.info(position.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Pharmacy> parsePharmacies(Document doc) {
        List<Pharmacy> pharmacies = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("pharmacy");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getAttribute("pharmacy_id"));
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String address = element.getElementsByTagName("address").item(0).getTextContent();
                int phoneNumber = Integer.parseInt(
                        element.getElementsByTagName("phone_number").item(0).getTextContent());

                Pharmacy pharmacy = new Pharmacy();
                pharmacy.setPharmacyID(id);
                pharmacy.setName(name);
                pharmacy.setAddress(address);
                pharmacy.setPhoneNumber(phoneNumber);

                pharmacies.add(pharmacy);
            }
        }

        return pharmacies;
    }

    private static List<Position> parsePositions(Document doc) {
        List<Position> positions = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("position");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getAttribute("position_id"));
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double salary = Double.parseDouble(
                        element.getElementsByTagName("salary").item(0).getTextContent());

                Position position = new Position();
                position.setPositionID(id);
                position.setName(name);
                position.setSalary(salary);

                positions.add(position);
            }
        }

        return positions;
    }
}