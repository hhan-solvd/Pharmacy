package com.solvd.app.interfaces;

import com.solvd.app.models.Pharmacy;

public interface IPharmacyDAO extends IBaseDAO<Pharmacy> {

    Pharmacy getPharmacyByAddress(String address);
}
