package com.solvd.app.daofactories;

import com.solvd.app.interfaces.IBaseDAO;

public abstract class AbstractDAOFactory {

    public abstract IBaseDAO<?> getDAO(String type);
}


