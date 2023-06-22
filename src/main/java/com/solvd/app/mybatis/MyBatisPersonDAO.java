package com.solvd.app.mybatis;

import com.solvd.app.interfaces.IPersonDAO;
import com.solvd.app.models.Person;
import com.solvd.app.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisPersonDAO implements IPersonDAO {

    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void createEntity(Person person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            personDAO.createEntity(person);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Person person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            personDAO.updateEntity(person);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteEntityByID(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            personDAO.deleteEntityByID(id);
            sqlSession.commit();
        }
    }

    @Override
    public Person getEntityByID(int id) {
        Person person;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            person = personDAO.getEntityByID(id);
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        List<Person> personList;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            personList = personDAO.getAll();
        }
        return personList;
    }

    @Override
    public Person getPersonByNameAndPhoneNumber(String name, int phoneNumber) {
        Person person;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO personDAO = sqlSession.getMapper(IPersonDAO.class);
            person = personDAO.getPersonByNameAndPhoneNumber(name, phoneNumber);
        }
        return person;
    }
}
