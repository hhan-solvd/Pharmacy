package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements IBaseDAO<Doctor> {
    private static final Logger LOGGER = LogManager.getLogger(DoctorDAO.class);
    private ConnectionPool connectionPool;
    private PersonDAO personDAO;
    private SpecialtyDAO specialtyDAO;

    public DoctorDAO(ConnectionPool connectionPool, PersonDAO personDAO, SpecialtyDAO specialtyDAO) {
        this.connectionPool = connectionPool;
        this.personDAO = personDAO;
        this.specialtyDAO = specialtyDAO;
    }

    public void createEntity(Doctor doctor) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO doctors (specialty_id, person_id, years_of_experience) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, doctor.getSpecialty().getSpecialtyID());
        preparedStatement.setInt(2, doctor.getPerson().getPersonID());
        preparedStatement.setInt(3, doctor.getYearsOfExperience());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                doctor.setDoctorID(newKey.getInt(1));
                LOGGER.info("Created a new doctor with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Doctor getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Doctor doctor = new Doctor();
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

        connectionPool.releaseConnection(connection);

        return doctor;
    }

    public void updateEntity(Doctor doctor) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE doctors SET specialty_id = ?, person_id = ?, years_of_experience = ? WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctor.getSpecialty().getSpecialtyID());
        preparedStatement.setInt(2, doctor.getPerson().getPersonID());
        preparedStatement.setInt(3, doctor.getYearsOfExperience());
        preparedStatement.setInt(4, doctor.getDoctorID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the doctor with ID number " + doctor.getDoctorID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the doctor with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Doctor> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Doctor> doctorList = new ArrayList<>();

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

        connectionPool.releaseConnection(connection);

        return doctorList;
    }
}
