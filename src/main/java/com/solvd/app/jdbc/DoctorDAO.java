package com.solvd.app.jdbc;

import com.solvd.app.interfaces.IDoctorDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements IDoctorDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private PersonDAO personDAO = new PersonDAO();
    private SpecialtyDAO specialtyDAO = new SpecialtyDAO();

    @Override
    public void createEntity(Doctor doctor) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO doctors (specialty_id, person_id, years_of_experience) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, doctor.getSpecialty().getSpecialtyID());
            preparedStatement.setInt(2, doctor.getPerson().getPersonID());
            preparedStatement.setInt(3, doctor.getYearsOfExperience());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    doctor.setDoctorID(newKey.getInt(1));
                    LOGGER.info("Created a new doctor with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create doctor: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Doctor getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Doctor doctor = new Doctor();

        try {
            String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                doctor.setDoctorID(resultSet.getInt("doctor_id"));
                doctor.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                doctor.setSpecialty(specialtyDAO.getEntityByID(resultSet.getInt("specialty_id")));
                doctor.setYearsOfExperience(resultSet.getInt("years_of_experience"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get doctor: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return doctor;
    }

    @Override
    public void updateEntity(Doctor doctor) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE doctors SET specialty_id = ?, person_id = ?, years_of_experience = ? " +
                    "WHERE doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctor.getSpecialty().getSpecialtyID());
            preparedStatement.setInt(2, doctor.getPerson().getPersonID());
            preparedStatement.setInt(3, doctor.getYearsOfExperience());
            preparedStatement.setInt(4, doctor.getDoctorID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the doctor with ID number " + doctor.getDoctorID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update doctor: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM doctors WHERE doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the doctor with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete doctor: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Doctor> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Doctor> doctorList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM doctors";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorID(resultSet.getInt("doctor_id"));
                doctor.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                doctor.setSpecialty(specialtyDAO.getEntityByID(resultSet.getInt("specialty_id")));
                doctor.setYearsOfExperience(resultSet.getInt("years_of_experience"));

                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all doctors: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return doctorList;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        Connection connection = connectionPool.getConnection();
        List<Doctor> doctorList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM doctors d INNER JOIN specialties s " +
                    "ON d.specialty_id = s.specialty_id WHERE s.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, specialty);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorID(resultSet.getInt("doctor_id"));
                doctor.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                doctor.setSpecialty(specialtyDAO.getEntityByID(resultSet.getInt("specialty_id")));
                doctor.setYearsOfExperience(resultSet.getInt("years_of_experience"));
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get doctors by specialty: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return doctorList;
    }
}
