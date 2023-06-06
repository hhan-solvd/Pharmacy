package com.solvd.app.services;

import com.solvd.app.daos.ManufacturerDAO;
import com.solvd.app.models.Manufacturer;

import java.sql.SQLException;
import java.util.List;

public class ManufacturerService {
    private ManufacturerDAO manufacturerDAO;

    public ManufacturerService(ManufacturerDAO manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    public void createManufacturer(Manufacturer manufacturer) throws SQLException {
        manufacturerDAO.createEntity(manufacturer);
    }

    public Manufacturer getManufacturerByID(int id) throws SQLException {
        return manufacturerDAO.getEntityByID(id);
    }

    public void updateManufacturer(Manufacturer manufacturer) throws SQLException {
        manufacturerDAO.updateEntity(manufacturer);
    }

    public void deleteManufacturerByID(int id) throws SQLException {
        manufacturerDAO.deleteEntityByID(id);
    }

    public List<Manufacturer> getAllManufacturers() throws SQLException {
        return manufacturerDAO.getAll();
    }
}
