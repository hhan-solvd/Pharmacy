package com.solvd.app.interfaces;

import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;

import java.util.List;

public interface IDrugDAO extends IBaseDAO<Drug> {

    List<Drug> getDrugsByManufacturer(Manufacturer manufacturer);
}
