package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IPrescriptionDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.models.Prescription;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PrescriptionDAO implements IPrescriptionDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Prescription prescription) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescriptionDAO.createEntity(prescription);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Prescription prescription) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescriptionDAO.updateEntity(prescription);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescriptionDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Prescription getEntityByID(int id) {
        Prescription prescription;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescription = prescriptionDAO.getEntityByID(id);
        }
        return prescription;
    }

    @Override
    public List<Prescription> getAll() {
        List<Prescription> prescriptionList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescriptionList = prescriptionDAO.getAll();
        }
        return prescriptionList;
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        List<Prescription> prescriptionList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionDAO prescriptionDAO = sqlSession.getMapper(IPrescriptionDAO.class);
            prescriptionList = prescriptionDAO.getPrescriptionsByDoctor(doctor);
        }
        return prescriptionList;
    }
}
