package com.solvd.app.daos;

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
    private ConnectionPool connectionPool;
    private PersonDAO personDAO;
    private PharmacyDAO pharmacyDAO;
    private PositionDAO positionDAO;

    public StaffDAO(ConnectionPool connectionPool, PersonDAO personDAO, PharmacyDAO pharmacyDAO,
                    PositionDAO positionDAO) {
        this.connectionPool = connectionPool;
        this.personDAO = personDAO;
        this.pharmacyDAO = pharmacyDAO;
        this.positionDAO = positionDAO;
    }

    public void createEntity(Staff staff) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO staff (pharmacy_id, person_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, staff.getPharmacy().getPharmacyID());
        preparedStatement.setInt(2, staff.getPerson().getPersonID());
        preparedStatement.setInt(3, staff.getPosition().getPositionID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                staff.setStaffID(newKey.getInt(1));
                LOGGER.info("Created a new staff with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Staff getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Staff staff = new Staff();
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

        connectionPool.releaseConnection(connection);

        return staff;
    }

    public void updateEntity(Staff staff) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE staff SET pharmacy_id = ?, person_id = ?, position_id = ? WHERE staff_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, staff.getPharmacy().getPharmacyID());
        preparedStatement.setInt(2, staff.getPerson().getPersonID());
        preparedStatement.setInt(3, staff.getPosition().getPositionID());
        preparedStatement.setInt(4, staff.getStaffID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the staff with ID number " + staff.getStaffID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM staff WHERE staff_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the staff with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Staff> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Staff> staffList = new ArrayList<>();

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

        connectionPool.releaseConnection(connection);

        return staffList;
    }

    public List<Staff> getStaffByPharmacy(Pharmacy pharmacy) throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Staff> staffList = new ArrayList<>();

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

        connectionPool.releaseConnection(connection);

        return staffList;
    }
}
