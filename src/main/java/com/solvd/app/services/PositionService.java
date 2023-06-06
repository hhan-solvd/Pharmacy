package com.solvd.app.services;

import com.solvd.app.daos.PositionDAO;
import com.solvd.app.models.Position;

import java.sql.SQLException;
import java.util.List;

public class PositionService {
    private PositionDAO positionDAO;

    public PositionService(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    public void createPosition(Position position) throws SQLException {
        positionDAO.createEntity(position);
    }

    public Position getPositionByID(int id) throws SQLException {
        return positionDAO.getEntityByID(id);
    }

    public void updatePosition(Position position) throws SQLException {
        positionDAO.updateEntity(position);
    }

    public void deletePositionByID(int id) throws SQLException {
        positionDAO.deleteEntityByID(id);
    }

    public List<Position> getAllPositions() throws SQLException {
        return positionDAO.getAll();
    }
}
