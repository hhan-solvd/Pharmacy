package com.solvd.app.services;

import com.solvd.app.daofactories.DBFactoryGenerator;
import com.solvd.app.enums.DAOType;
import com.solvd.app.interfaces.IDoctorDAO;
import com.solvd.app.models.Doctor;

import java.util.List;

public class DoctorService {

    private IDoctorDAO doctorDAO;
    private PersonService personService;
    private SpecialtyService specialtyService;

    public DoctorService(DAOType type) {
        this.doctorDAO = (IDoctorDAO) DBFactoryGenerator.getFactory(type).getDAO("Doctor");
        this.personService = new PersonService(type);
        this.specialtyService = new SpecialtyService(type);
    }

    public void createDoctor(Doctor doctor) {
        if (doctor.getPerson().getPersonID() == 0) {
            personService.createPerson(doctor.getPerson());
        }

        if (doctor.getSpecialty().getSpecialtyID() == 0) {
            specialtyService.createSpecialty(doctor.getSpecialty());
        }

        doctorDAO.createEntity(doctor);
    }

    public Doctor getDoctorByID(int id) {
        return doctorDAO.getEntityByID(id);
    }

    public void updateDoctor(Doctor doctor) {
        if (doctor.getPerson().getPersonID() == 0) {
            personService.createPerson(doctor.getPerson());
        }

        if (doctor.getSpecialty().getSpecialtyID() == 0) {
            specialtyService.createSpecialty(doctor.getSpecialty());
        }

        doctorDAO.updateEntity(doctor);
    }

    public void deleteDoctorByID(int id) {
        doctorDAO.deleteEntityByID(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAll();
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorDAO.getDoctorsBySpecialty(specialty);
    }
}

