package com.solvd.app.interfaces;

import com.solvd.app.models.Manufacturer;

import java.util.List;

public interface IManufacturerDAO extends IBaseDAO<Manufacturer> {

    List<Manufacturer> getManufacturersByName(String name);
}
