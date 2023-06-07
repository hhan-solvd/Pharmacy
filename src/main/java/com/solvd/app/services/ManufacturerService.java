package com.solvd.app.services;

import com.solvd.app.dao.ManufacturerDAO;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class ManufacturerService {
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAO();

    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.createEntity(manufacturer);
    }

    public Manufacturer getManufacturerByID(int id) {
        return manufacturerDAO.getEntityByID(id);
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.updateEntity(manufacturer);
    }

    public void deleteManufacturerByID(int id) {
        manufacturerDAO.deleteEntityByID(id);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDAO.getAll();
    }
}
