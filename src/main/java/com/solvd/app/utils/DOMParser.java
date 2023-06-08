package com.solvd.app.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class DOMParser {

    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void parseXMLFile() {

        try {
            File xmlFile = new File("src/main/resources/data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            LOGGER.info("Root element: " + doc.getDocumentElement().getNodeName());

            parseElements(doc, "pharmacy");
            parseElements(doc, "position");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseElements(Document doc, String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                LOGGER.info("Current Element: " + element.getNodeName());
                printElementDetails(element);
            }
        }
    }

    private static void printElementDetails(Element element) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) node;
                LOGGER.info(childElement.getNodeName() + " : " + childElement.getTextContent());
            }
        }
    }
}

