package com.solvd.app.jdbc;

import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.models.Staff;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO implements IStaffDAO {

    private static final Logger LOGGER = LogManager.getLogger(StaffDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private PersonDAO personDAO = new PersonDAO();
    private PharmacyDAO pharmacyDAO = new PharmacyDAO();
    private PositionDAO positionDAO = new PositionDAO();

    @Override
    public void createEntity(Staff staff) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO staff (pharmacy_id, person_id, position_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, staff.getPharmacy().getPharmacyID());
            preparedStatement.setInt(2, staff.getPerson().getPersonID());
            preparedStatement.setInt(3, staff.getPosition().getPositionID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating staff failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    staff.setStaffID(newKey.getInt(1));
                    LOGGER.info("Created a new staff with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating staff failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create staff: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Staff getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Staff staff = new Staff();

        try {
            String sql = "SELECT * FROM staff WHERE staff_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                staff.setStaffID(resultSet.getInt("staff_id"));
                staff.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                staff.setPharmacy(pharmacyDAO.getEntityByID(resultSet.getInt("pharmacy_id")));
                staff.setPosition(positionDAO.getEntityByID(resultSet.getInt("position_id")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get staff: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return staff;
    }

    @Override
    public void updateEntity(Staff staff) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE staff SET pharmacy_id = ?, person_id = ?, position_id = ? WHERE staff_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staff.getPharmacy().getPharmacyID());
            preparedStatement.setInt(2, staff.getPerson().getPersonID());
            preparedStatement.setInt(3, staff.getPosition().getPositionID());
            preparedStatement.setInt(4, staff.getStaffID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating staff failed, no rows affected.");
            } else {
                LOGGER.info("Updated the staff with ID number " + staff.getStaffID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update staff: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM staff WHERE staff_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting staff failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the staff with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete staff: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Staff> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Staff> staffList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM staff";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setStaffID(resultSet.getInt("staff_id"));
                staff.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                staff.setPharmacy(pharmacyDAO.getEntityByID(resultSet.getInt("pharmacy_id")));
                staff.setPosition(positionDAO.getEntityByID(resultSet.getInt("position_id")));

                staffList.add(staff);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all staff: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return staffList;
    }

    @Override
    public List<Staff> getStaffByPharmacy(Pharmacy pharmacy) {
        Connection connection = connectionPool.getConnection();
        List<Staff> staffList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM staff WHERE pharmacy_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pharmacy.getPharmacyID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setStaffID(resultSet.getInt("staff_id"));
                staff.setPerson(personDAO.getEntityByID(resultSet.getInt("person_id")));
                staff.setPharmacy(pharmacyDAO.getEntityByID(resultSet.getInt("pharmacy_id")));
                staff.setPosition(positionDAO.getEntityByID(resultSet.getInt("position_id")));

                staffList.add(staff);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get staff by pharmacy: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return staffList;
    }
}
