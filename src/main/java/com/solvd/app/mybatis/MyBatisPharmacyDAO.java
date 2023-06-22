package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IPharmacyDAO;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisPharmacyDAO implements IPharmacyDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Pharmacy pharmacy) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacyDao.createEntity(pharmacy);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Pharmacy pharmacy) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacyDao.updateEntity(pharmacy);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacyDao.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Pharmacy getEntityByID(int id) {
        Pharmacy pharmacy;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacy = pharmacyDao.getEntityByID(id);
        }
        return pharmacy;
    }

    @Override
    public List<Pharmacy> getAll() {
        List<Pharmacy> pharmacyList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacyList = pharmacyDao.getAll();
        }
        return pharmacyList;
    }

    @Override
    public Pharmacy getPharmacyByAddress(String address) {
        Pharmacy pharmacy;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPharmacyDAO pharmacyDao = sqlSession.getMapper(IPharmacyDAO.class);
            pharmacy = pharmacyDao.getPharmacyByAddress(address);
        }
        return pharmacy;
    }
}
