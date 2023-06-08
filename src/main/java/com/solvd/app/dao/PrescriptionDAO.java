package com.solvd.app.dao;

import com.solvd.app.interfaces.IPrescriptionDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.models.Prescription;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO implements IPrescriptionDAO {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    public void createEntity(Prescription prescription) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO prescriptions (prescription_date, doctor_id, customer_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(prescription.getPrescriptionDate().getTime()));
            preparedStatement.setInt(2, prescription.getDoctor().getDoctorID());
            preparedStatement.setInt(3, prescription.getCustomer().getCustomerID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    prescription.setPrescriptionID(newKey.getInt(1));
                    LOGGER.info("Created a new prescription with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create prescription: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Prescription getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Prescription prescription = new Prescription();

        try {
            String sql = "SELECT * FROM prescriptions WHERE prescription_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                prescription.setPrescriptionID(resultSet.getInt("prescription_id"));
                prescription.setPrescriptionDate(resultSet.getDate("prescription_date"));
                prescription.setDoctor(doctorDAO.getEntityByID(resultSet.getInt("doctor_id")));
                prescription.setCustomer(customerDAO.getEntityByID(resultSet.getInt("customer_id")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get prescription: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescription;
    }

    public void updateEntity(Prescription prescription) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE prescriptions SET prescription_date = ?, doctor_id = ?, customer_id = ? " +
                    "WHERE prescription_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(prescription.getPrescriptionDate().getTime()));
            preparedStatement.setInt(2, prescription.getDoctor().getDoctorID());
            preparedStatement.setInt(3, prescription.getCustomer().getCustomerID());
            preparedStatement.setInt(4, prescription.getPrescriptionID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the prescription with ID number " + prescription.getPrescriptionID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update prescription: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the prescription with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete prescription: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<Prescription> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Prescription> prescriptionList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM prescriptions";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionID(resultSet.getInt("prescription_id"));
                prescription.setPrescriptionDate(resultSet.getDate("prescription_date"));
                prescription.setDoctor(doctorDAO.getEntityByID(resultSet.getInt("doctor_id")));
                prescription.setCustomer(customerDAO.getEntityByID(resultSet.getInt("customer_id")));

                prescriptionList.add(prescription);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all prescriptions: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescriptionList;
    }

    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        Connection connection = connectionPool.getConnection();
        List<Prescription> prescriptionList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM prescriptions WHERE doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctor.getDoctorID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionID(resultSet.getInt("prescription_id"));
                prescription.setPrescriptionDate(resultSet.getDate("prescription_date"));
                prescription.setDoctor(doctorDAO.getEntityByID(resultSet.getInt("doctor_id")));
                prescription.setCustomer(customerDAO.getEntityByID(resultSet.getInt("customer_id")));

                prescriptionList.add(prescription);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get prescriptions by doctor: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescriptionList;
    }
}
