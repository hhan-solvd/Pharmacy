package com.solvd.app.mybatis;

import com.solvd.app.interfaces.ISpecialtyDAO;
import com.solvd.app.models.Specialty;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisSpecialtyDAO implements ISpecialtyDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Specialty specialty) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.createEntity(specialty);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Specialty specialty) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.updateEntity(specialty);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Specialty getEntityByID(int id) {
        Specialty specialty;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialty = specialtyDAO.getEntityByID(id);
        }
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialtyList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyList = specialtyDAO.getAll();
        }
        return specialtyList;
    }

    @Override
    public Specialty getSpecialtyByName(String name) {
        Specialty specialty;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialty = specialtyDAO.getSpecialtyByName(name);
        }
        return specialty;
    }
}