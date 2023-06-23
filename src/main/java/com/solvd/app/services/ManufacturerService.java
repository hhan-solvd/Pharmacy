package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IManufacturerDAO;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class ManufacturerService {

    private IManufacturerDAO manufacturerDAO;

    public ManufacturerService(DAOType type) {
        this.manufacturerDAO = (IManufacturerDAO) DBFactoryGenerator.getFactory(type).getDAO("Manufacturer");
    }

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

    public List<Manufacturer> getManufacturersByName(String name) {
        return manufacturerDAO.getManufacturersByName(name);
    }
}