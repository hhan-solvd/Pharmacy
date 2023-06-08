package com.solvd.app.interfaces;

import java.util.List;

public interface IBaseDAO<T> {
    void createEntity(T t);

    T getEntityByID(int id);

    void updateEntity(T t);

    void deleteEntityByID(int id);

    List<T> getAll();
}
