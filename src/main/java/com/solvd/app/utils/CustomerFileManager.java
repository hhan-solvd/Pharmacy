package com.solvd.app.utils;

import com.solvd.app.interfaces.ICustomerDecorator;

import java.io.FileWriter;
import java.io.IOException;

public class CustomerFileManager {
    public void saveCustomerToFile(ICustomerDecorator customer, String filename) {
        String customerData = customer.getCustomerID() + ","
                + customer.getPerson().toString() + ","
                + customer.getDiscountPercentage();

        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(customerData + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
