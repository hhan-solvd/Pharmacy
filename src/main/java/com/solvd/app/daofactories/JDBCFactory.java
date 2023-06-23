package com.solvd.app.daofactories;

public class JDBCFactory extends AbstractDAOFactory {

    @Override
    protected String getFullClassName(String modelName) {
        return "com.solvd.app.jdbc." + modelName + "DAO";
    }
}

