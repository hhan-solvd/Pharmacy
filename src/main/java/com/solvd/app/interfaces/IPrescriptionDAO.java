package com.solvd.app.interfaces;

import com.solvd.app.models.Doctor;
import com.solvd.app.models.Prescription;

import java.util.List;

public interface IPrescriptionDAO extends IBaseDAO<Prescription> {

    List<Prescription> getPrescriptionsByDoctor(Doctor doctor);
}
