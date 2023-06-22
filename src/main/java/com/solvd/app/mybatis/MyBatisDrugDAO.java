package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IDrugDAO;
import com.solvd.app.models.Drug;
import com.solvd.app.models.Manufacturer;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisDrugDAO implements IDrugDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Drug drug) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drugDAO.createEntity(drug);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Drug drug) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drugDAO.updateEntity(drug);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drugDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Drug getEntityByID(int id) {
        Drug drug;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drug = drugDAO.getEntityByID(id);
        }
        return drug;
    }

    @Override
    public List<Drug> getAll() {
        List<Drug> drugList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drugList = drugDAO.getAll();
        }
        return drugList;
    }

    @Override
    public List<Drug> getDrugsByManufacturer(Manufacturer manufacturer) {
        List<Drug> drugList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugDAO drugDAO = sqlSession.getMapper(IDrugDAO.class);
            drugList = drugDAO.getDrugsByManufacturer(manufacturer);
        }
        return drugList;
    }
}
