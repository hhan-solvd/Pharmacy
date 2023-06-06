package com.solvd.app.daos;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.DrugCategory;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugCategoryDAO implements IBaseDAO<DrugCategory> {
    private static final Logger LOGGER = LogManager.getLogger(DrugCategoryDAO.class);
    private ConnectionPool connectionPool;

    public DrugCategoryDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(DrugCategory drugCategory) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO drug_categories (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, drugCategory.getName());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                drugCategory.setCategoryID(newKey.getInt(1));
                LOGGER.info("Created a new drug category with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }


    public DrugCategory getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        DrugCategory drugCategory = new DrugCategory();
        String sql = "SELECT * FROM drug_categories WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            drugCategory.setCategoryID(resultSet.getInt("category_id"));
            drugCategory.setName(resultSet.getString("name"));
        }

        connectionPool.releaseConnection(connection);

        return drugCategory;
    }

    public void updateEntity(DrugCategory drugCategory) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE drug_categories SET name = ? WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, drugCategory.getName());
        preparedStatement.setInt(2, drugCategory.getCategoryID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the drug category with ID number " + drugCategory.getCategoryID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM drug_categories WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the drug category with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<DrugCategory> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<DrugCategory> drugCategoryList = new ArrayList<>();

        String sql = "SELECT * FROM drug_categories";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            DrugCategory drugCategory = new DrugCategory();
            drugCategory.setCategoryID(resultSet.getInt("category_id"));
            drugCategory.setName(resultSet.getString("name"));

            drugCategoryList.add(drugCategory);
        }

        connectionPool.releaseConnection(connection);

        return drugCategoryList;
    }
}
