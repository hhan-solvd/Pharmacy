package com.solvd.app.services;

import com.solvd.app.enums.DAOType;
import com.solvd.app.jdbc.PositionDAO;
import com.solvd.app.interfaces.IPositionDAO;
import com.solvd.app.mybatis.MyBatisPositionDAO;
import com.solvd.app.models.Position;

import java.util.List;

public class PositionService {

    private IPositionDAO positionDAO;

    public PositionService(DAOType type) {
        switch (type) {
            case JDBC -> this.positionDAO = new PositionDAO();
            case MYBATIS -> this.positionDAO = new MyBatisPositionDAO();
            default -> throw new IllegalArgumentException("Invalid DAO type");
        }
    }

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

    public double getSalaryByPositionName(String name) {
        return positionDAO.getSalaryByPositionName(name);
    }
}
