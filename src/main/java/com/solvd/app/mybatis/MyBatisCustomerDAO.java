package com.solvd.app.mybatis;

import com.solvd.app.interfaces.ICustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisCustomerDAO implements ICustomerDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.createEntity(customer);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.updateEntity(customer);
            sqlSession.commit();
        }
    }

    @Override
    public Customer getEntityByID(int id) {
        Customer customer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getEntityByID(id);
        }
        return customer;
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerList = customerDAO.getAll();
        }
        return customerList;
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customerList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerList = customerDAO.getCustomersByName(name);
        }
        return customerList;
    }
}
