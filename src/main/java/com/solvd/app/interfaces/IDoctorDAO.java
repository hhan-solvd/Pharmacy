package com.solvd.app.interfaces;

import com.solvd.app.models.Doctor;

import java.util.List;

public interface IDoctorDAO extends IBaseDAO<Doctor> {

    List<Doctor> getDoctorsBySpecialty(String specialty);
}
