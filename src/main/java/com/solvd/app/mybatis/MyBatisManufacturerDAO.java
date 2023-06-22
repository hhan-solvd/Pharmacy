package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IManufacturerDAO;
import com.solvd.app.models.Manufacturer;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisManufacturerDAO implements IManufacturerDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Manufacturer manufacturer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturerDAO.createEntity(manufacturer);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Manufacturer manufacturer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturerDAO.updateEntity(manufacturer);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturerDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Manufacturer getEntityByID(int id) {
        Manufacturer manufacturer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturer = manufacturerDAO.getEntityByID(id);
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturerList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturerList = manufacturerDAO.getAll();
        }
        return manufacturerList;
    }

    @Override
    public List<Manufacturer> getManufacturersByName(String name) {
        List<Manufacturer> manufacturerList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IManufacturerDAO manufacturerDAO = sqlSession.getMapper(IManufacturerDAO.class);
            manufacturerList = manufacturerDAO.getManufacturersByName(name);
        }
        return manufacturerList;
    }
}
