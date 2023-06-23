package com.solvd.app.mybatis;

import com.solvd.app.interfaces.ISupplierDAO;
import com.solvd.app.models.Supplier;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SupplierDAO implements ISupplierDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Supplier supplier) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplierDAO.createEntity(supplier);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Supplier supplier) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplierDAO.updateEntity(supplier);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplierDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Supplier getEntityByID(int id) {
        Supplier supplier;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplier = supplierDAO.getEntityByID(id);
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supplierList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplierList = supplierDAO.getAll();
        }
        return supplierList;
    }

    @Override
    public Supplier getSupplierByAddress(String address) {
        Supplier supplier;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ISupplierDAO supplierDAO = sqlSession.getMapper(ISupplierDAO.class);
            supplier = supplierDAO.getSupplierByAddress(address);
        }
        return supplier;
    }
}