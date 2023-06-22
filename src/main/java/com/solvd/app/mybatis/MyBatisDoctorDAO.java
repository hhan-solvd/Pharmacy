package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IDoctorDAO;
import com.solvd.app.models.Doctor;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisDoctorDAO implements IDoctorDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Doctor doctor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctorDAO.createEntity(doctor);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Doctor doctor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctorDAO.updateEntity(doctor);
            sqlSession.commit();
        }
    }

    @Override
    public Doctor getEntityByID(int id) {
        Doctor doctor;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctor = doctorDAO.getEntityByID(id);
        }
        return doctor;
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctorDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctorList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctorList = doctorDAO.getAll();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        List<Doctor> doctorList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDoctorDAO doctorDAO = sqlSession.getMapper(IDoctorDAO.class);
            doctorList = doctorDAO.getDoctorsBySpecialty(specialty);
        }
        return doctorList;
    }
}
