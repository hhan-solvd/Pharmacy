package com.solvd.app.dao;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.models.Position;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements IBaseDAO<Position> {
    private static final Logger LOGGER = LogManager.getLogger(PositionDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public void createEntity(Position position) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "INSERT INTO positions (name, salary) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, position.getName());
            preparedStatement.setDouble(2, position.getSalary());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Creating failed, no rows affected.");
            }

            try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
                if (newKey.next()) {
                    position.setPositionID(newKey.getInt(1));
                    LOGGER.info("Created a new position with ID: " + newKey.getInt(1));
                } else {
                    LOGGER.error("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to create position: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Position getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        Position position = new Position();

        try {
            String sql = "SELECT * FROM positions WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                position.setPositionID(resultSet.getInt("position_id"));
                position.setName(resultSet.getString("name"));
                position.setSalary(resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get position: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return position;
    }

    public void updateEntity(Position position) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "UPDATE positions SET name = ?, salary = ? WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, position.getName());
            preparedStatement.setDouble(2, position.getSalary());
            preparedStatement.setInt(3, position.getPositionID());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Updating failed, no rows affected.");
            } else {
                LOGGER.info("Updated the position with ID number " + position.getPositionID());
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to update position: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void deleteEntityByID(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            String sql = "DELETE FROM positions WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("Deleting failed, no rows affected.");
            } else {
                LOGGER.info("Deleted the position with ID number " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to delete position: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<Position> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Position> positionList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM positions";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Position position = new Position();
                position.setPositionID(resultSet.getInt("position_id"));
                position.setName(resultSet.getString("name"));
                position.setSalary(resultSet.getDouble("salary"));

                positionList.add(position);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when trying to get all positions: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return positionList;
    }
}
