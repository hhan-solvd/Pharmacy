package com.solvd.app.dao;

import com.solvd.app.interfaces.IPrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionItemDAO implements IPrescriptionItemDAO {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionItemDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private DrugDAO drugDAO = new DrugDAO();

    public void createEntity(PrescriptionItem prescriptionItem) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO prescription_items (quantity_prescribed, prescription_id, drug_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, prescriptionItem.getQuantityPrescribed());
            preparedStatement.setInt(2, prescriptionItem.getPrescription().getPrescriptionID());
            preparedStatement.setInt(3, prescriptionItem.getDrug().getDrugID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    prescriptionItem.setPrescriptionItemID(newKey.getInt(1));
                    LOGGER.info("Created a new prescription item with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create prescription item: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public PrescriptionItem getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        PrescriptionItem prescriptionItem = new PrescriptionItem();

        try {
            String sql = "SELECT * FROM prescription_items WHERE prescription_item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                prescriptionItem.setPrescriptionItemID(resultSet.getInt("prescription_item_id"));
                prescriptionItem.setQuantityPrescribed(resultSet.getInt("quantity_prescribed"));
                prescriptionItem.setPrescription(prescriptionDAO.getEntityByID(
                        resultSet.getInt("prescription_id")));
                prescriptionItem.setDrug(drugDAO.getEntityByID(resultSet.getInt("drug_id")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get prescription item: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescriptionItem;
    }

    public void updateEntity(PrescriptionItem prescriptionItem) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE prescription_items SET quantity_prescribed = ?, prescription_id = ?, " +
                    "drug_id = ? WHERE prescription_item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, prescriptionItem.getQuantityPrescribed());
            preparedStatement.setInt(2, prescriptionItem.getPrescription().getPrescriptionID());
            preparedStatement.setInt(3, prescriptionItem.getDrug().getDrugID());
            preparedStatement.setInt(4, prescriptionItem.getPrescriptionItemID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the prescription item with ID number " + prescriptionItem.getPrescriptionItemID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update prescription item: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM prescription_items WHERE prescription_item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the prescription item with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete prescription item: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<PrescriptionItem> getAll() {
        Connection connection = connectionPool.getConnection();
        List<PrescriptionItem> prescriptionItemList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM prescription_items";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PrescriptionItem prescriptionItem = new PrescriptionItem();
                prescriptionItem.setPrescriptionItemID(resultSet.getInt("prescription_item_id"));
                prescriptionItem.setQuantityPrescribed(resultSet.getInt("quantity_prescribed"));
                prescriptionItem.setPrescription(prescriptionDAO.getEntityByID(
                        resultSet.getInt("prescription_id")));
                prescriptionItem.setDrug(drugDAO.getEntityByID(resultSet.getInt("drug_id")));

                prescriptionItemList.add(prescriptionItem);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all prescription items: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescriptionItemList;
    }

    public List<PrescriptionItem> getItemsByPrescription(Prescription prescription) {
        Connection connection = connectionPool.getConnection();
        List<PrescriptionItem> prescriptionItemList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM prescription_items WHERE prescription_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, prescription.getPrescriptionID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PrescriptionItem prescriptionItem = new PrescriptionItem();
                prescriptionItem.setPrescriptionItemID(resultSet.getInt("prescription_item_id"));
                prescriptionItem.setQuantityPrescribed(resultSet.getInt("quantity_prescribed"));
                prescriptionItem.setPrescription(prescriptionDAO.getEntityByID(
                        resultSet.getInt("prescription_id")));
                prescriptionItem.setDrug(drugDAO.getEntityByID(resultSet.getInt("drug_id")));

                prescriptionItemList.add(prescriptionItem);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get prescription items by prescription: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return prescriptionItemList;
    }
}
