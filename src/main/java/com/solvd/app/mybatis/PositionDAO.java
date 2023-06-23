package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IPositionDAO;
import com.solvd.app.models.Position;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PositionDAO implements IPositionDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Position position) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            positionDAO.createEntity(position);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Position position) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            positionDAO.updateEntity(position);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            positionDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Position getEntityByID(int id) {
        Position position;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            position = positionDAO.getEntityByID(id);
        }
        return position;
    }

    @Override
    public List<Position> getAll() {
        List<Position> positionList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            positionList = positionDAO.getAll();
        }
        return positionList;
    }

    @Override
    public double getSalaryByPositionName(String name) {
        double salary;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPositionDAO positionDAO = sqlSession.getMapper(IPositionDAO.class);
            salary = positionDAO.getSalaryByPositionName(name);
        }
        return salary;
    }
}
