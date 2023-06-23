package com.solvd.app.jdbc;

import com.solvd.app.interfaces.IDrugCategoryDAO;
import com.solvd.app.models.DrugCategory;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugCategoryDAO implements IDrugCategoryDAO {

    private static final Logger LOGGER = LogManager.getLogger(DrugCategoryDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void createEntity(DrugCategory drugCategory) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO drug_categories (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, drugCategory.getName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    drugCategory.setCategoryID(newKey.getInt(1));
                    LOGGER.info("Created a new drug category with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create drug category: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public DrugCategory getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        DrugCategory drugCategory = new DrugCategory();

        try {
            String sql = "SELECT * FROM drug_categories WHERE category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drugCategory.setCategoryID(resultSet.getInt("category_id"));
                drugCategory.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get drug category: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drugCategory;
    }

    @Override
    public void updateEntity(DrugCategory drugCategory) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE drug_categories SET name = ? WHERE category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, drugCategory.getName());
            preparedStatement.setInt(2, drugCategory.getCategoryID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the drug category with ID number " + drugCategory.getCategoryID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update drug category: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM drug_categories WHERE category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the drug category with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete drug category: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<DrugCategory> getAll() {
        Connection connection = connectionPool.getConnection();
        List<DrugCategory> drugCategoryList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM drug_categories";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DrugCategory drugCategory = new DrugCategory();
                drugCategory.setCategoryID(resultSet.getInt("category_id"));
                drugCategory.setName(resultSet.getString("name"));

                drugCategoryList.add(drugCategory);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all drug categories: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drugCategoryList;
    }

    @Override
    public DrugCategory getDrugCategoryByName(String name) {
        Connection connection = connectionPool.getConnection();
        DrugCategory drugCategory = new DrugCategory();

        try {
            String sql = "SELECT * FROM drug_categories WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drugCategory.setCategoryID(resultSet.getInt("category_id"));
                drugCategory.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get drug category by name: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return drugCategory;
    }
}
