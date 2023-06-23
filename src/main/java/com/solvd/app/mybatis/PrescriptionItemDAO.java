package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IPrescriptionItemDAO;
import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PrescriptionItemDAO implements IPrescriptionItemDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(PrescriptionItem prescriptionItem) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItemDAO.createEntity(prescriptionItem);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(PrescriptionItem prescriptionItem) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItemDAO.updateEntity(prescriptionItem);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItemDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public PrescriptionItem getEntityByID(int id) {
        PrescriptionItem prescriptionItem;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItem = prescriptionItemDAO.getEntityByID(id);
        }
        return prescriptionItem;
    }

    @Override
    public List<PrescriptionItem> getAll() {
        List<PrescriptionItem> prescriptionItemList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItemList = prescriptionItemDAO.getAll();
        }
        return prescriptionItemList;
    }

    @Override
    public List<PrescriptionItem> getItemsByPrescription(Prescription prescription) {
        List<PrescriptionItem> prescriptionItemList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPrescriptionItemDAO prescriptionItemDAO = sqlSession.getMapper(IPrescriptionItemDAO.class);
            prescriptionItemList = prescriptionItemDAO.getItemsByPrescription(prescription);
        }
        return prescriptionItemList;
    }
}
