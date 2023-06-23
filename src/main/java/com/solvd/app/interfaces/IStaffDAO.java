package com.solvd.app.interfaces;

import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;

import java.util.List;

public interface IStaffDAO extends IBaseDAO<Staff> {

    List<Staff> getStaffByPharmacy(Pharmacy pharmacy);
}
