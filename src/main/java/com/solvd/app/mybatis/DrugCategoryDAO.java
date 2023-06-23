package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IDrugCategoryDAO;
import com.solvd.app.models.DrugCategory;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DrugCategoryDAO implements IDrugCategoryDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(DrugCategory drugCategory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategoryDAO.createEntity(drugCategory);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(DrugCategory drugCategory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategoryDAO.updateEntity(drugCategory);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategoryDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public DrugCategory getEntityByID(int id) {
        DrugCategory drugCategory;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategory = drugCategoryDAO.getEntityByID(id);
        }
        return drugCategory;
    }

    @Override
    public List<DrugCategory> getAll() {
        List<DrugCategory> drugCategoryList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategoryList = drugCategoryDAO.getAll();
        }
        return drugCategoryList;
    }

    @Override
    public DrugCategory getDrugCategoryByName(String name) {
        DrugCategory drugCategory;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrugCategoryDAO drugCategoryDAO = sqlSession.getMapper(IDrugCategoryDAO.class);
            drugCategory = drugCategoryDAO.getDrugCategoryByName(name);
        }
        return drugCategory;
    }
}