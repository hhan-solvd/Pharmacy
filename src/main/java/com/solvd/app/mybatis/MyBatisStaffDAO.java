package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IStaffDAO;
import com.solvd.app.models.Staff;
import com.solvd.app.models.Pharmacy;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisStaffDAO implements IStaffDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Staff staff) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staffDAO.createEntity(staff);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Staff staff) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staffDAO.updateEntity(staff);
            sqlSession.commit();
        }
    }

    @Override
    public Staff getEntityByID(int id) {
        Staff staff;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staff = staffDAO.getEntityByID(id);
        }
        return staff;
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staffDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staffList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staffList = staffDAO.getAll();
        }
        return staffList;
    }

    @Override
    public List<Staff> getStaffByPharmacy(Pharmacy pharmacy) {
        List<Staff> staffList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IStaffDAO staffDAO = sqlSession.getMapper(IStaffDAO.class);
            staffList = staffDAO.getStaffByPharmacy(pharmacy);
        }
        return staffList;
    }
}
