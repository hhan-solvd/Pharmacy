package com.solvd.app.services;

import com.solvd.app.dao.PositionDAO;
import com.solvd.app.models.Position;

import java.util.List;

public class PositionService {
    private PositionDAO positionDAO = new PositionDAO();

    public void createPosition(Position position) {
        positionDAO.createEntity(position);
    }

    public Position getPositionByID(int id) {
        return positionDAO.getEntityByID(id);
    }

    public void updatePosition(Position position) {
        positionDAO.updateEntity(position);
    }

    public void deletePositionByID(int id) {
        positionDAO.deleteEntityByID(id);
    }

    public List<Position> getAllPositions() {
        return positionDAO.getAll();
    }
}
