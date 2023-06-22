package com.solvd.app.services;

import com.solvd.app.jdbc.ManufacturerDAO;
import com.solvd.app.interfaces.IManufacturerDAO;
import com.solvd.app.mybatis.MyBatisManufacturerDAO;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public class ManufacturerService {

    private IManufacturerDAO manufacturerDAO;

    public ManufacturerService() {
        this.manufacturerDAO = new ManufacturerDAO();
    }

    public ManufacturerService(MyBatisManufacturerDAO myBatisManufacturerDAO) {
        this.manufacturerDAO = myBatisManufacturerDAO;
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