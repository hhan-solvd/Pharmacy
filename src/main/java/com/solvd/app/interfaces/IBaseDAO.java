package com.solvd.app.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {
    void createEntity(T t) throws SQLException;

    T getEntityByID(int id) throws SQLException;

    void updateEntity(T t) throws SQLException;

    void deleteEntityByID(int id) throws SQLException;

    List<T> getAll() throws SQLException;
}
