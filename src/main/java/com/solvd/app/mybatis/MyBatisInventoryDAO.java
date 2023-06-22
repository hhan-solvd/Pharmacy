package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IInventoryDAO;
import com.solvd.app.models.Inventory;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisInventoryDAO implements IInventoryDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Inventory inventory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            inventoryDAO.createEntity(inventory);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Inventory inventory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            inventoryDAO.updateEntity(inventory);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            inventoryDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Inventory getEntityByID(int id) {
        Inventory inventory;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            inventory = inventoryDAO.getEntityByID(id);
        }
        return inventory;
    }

    @Override
    public List<Inventory> getAll() {
        List<Inventory> inventoryList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            inventoryList = inventoryDAO.getAll();
        }
        return inventoryList;
    }

    @Override
    public int getInventoryQuantityByID(int id) {
        int quantity;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IInventoryDAO inventoryDAO = sqlSession.getMapper(IInventoryDAO.class);
            quantity = inventoryDAO.getInventoryQuantityByID(id);
        }
        return quantity;
    }
}
