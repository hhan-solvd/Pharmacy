package com.solvd.app.daofactories;

public class MyBatisFactory extends AbstractDAOFactory {

    @Override
    protected String getFullClassName(String modelName) {
        return "com.solvd.app.mybatis.MyBatis" + modelName + "DAO";
    }
}
