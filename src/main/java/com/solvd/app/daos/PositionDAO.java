package com.solvd.app.daos;

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
    private ConnectionPool connectionPool;

    public PositionDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createEntity(Position position) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "INSERT INTO positions (name, salary) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, position.getName());
        preparedStatement.setDouble(2, position.getSalary());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating failed, no rows affected.");
        }

        try (ResultSet newKey = preparedStatement.getGeneratedKeys()) {
            if (newKey.next()) {
                position.setPositionID(newKey.getInt(1));
                LOGGER.info("Created a new position with ID: " + newKey.getInt(1));
            } else {
                throw new SQLException("Creating failed, no ID obtained.");
            }
        }

        connectionPool.releaseConnection(connection);
    }

    public Position getEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Position position = new Position();
        String sql = "SELECT * FROM positions WHERE position_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            position.setPositionID(resultSet.getInt("position_id"));
            position.setName(resultSet.getString("name"));
            position.setSalary(resultSet.getDouble("salary"));
        }

        connectionPool.releaseConnection(connection);

        return position;
    }

    public void updateEntity(Position position) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "UPDATE positions SET name = ?, salary = ? WHERE position_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, position.getName());
        preparedStatement.setDouble(2, position.getSalary());
        preparedStatement.setInt(3, position.getPositionID());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating failed, no rows affected.");
        } else {
            LOGGER.info("Updated the position with ID number " + position.getPositionID());
        }

        connectionPool.releaseConnection(connection);
    }

    public void deleteEntityByID(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "DELETE FROM positions WHERE position_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting failed, no rows affected.");
        } else {
            LOGGER.info("Deleted the position with ID number " + id);
        }

        connectionPool.releaseConnection(connection);
    }

    public List<Position> getAll() throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Position> positionList = new ArrayList<>();

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

        connectionPool.releaseConnection(connection);

        return positionList;
    }
}
