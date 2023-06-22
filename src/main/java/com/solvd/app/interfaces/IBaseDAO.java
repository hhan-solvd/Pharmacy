package com.solvd.app.interfaces;

import java.util.List;

public interface IBaseDAO<T> {

    void createEntity(T t);

    void updateEntity(T t);

    void deleteEntityByID(int id);

    T getEntityByID(int id);

    List<T> getAll();
}
