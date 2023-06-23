package com.solvd.app.daofactories;

import java.lang.reflect.Constructor;

public abstract class AbstractDAOFactory {

    public Object getDAO(String modelName) {
        try {
            Class<?> daoClass = Class.forName(getFullClassName(modelName));
            Constructor<?> constructor = daoClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // this method should return full class name for each modelName
    protected abstract String getFullClassName(String modelName);
}


