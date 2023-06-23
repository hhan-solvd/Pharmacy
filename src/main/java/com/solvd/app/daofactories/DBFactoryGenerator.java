package com.solvd.app.daofactories;

import com.solvd.app.enums.DAOType;

public class DBFactoryGenerator {

    public static AbstractDAOFactory getFactory(DAOType type) {
        return switch (type) {
            case JDBC -> new JDBCFactory();
            case MYBATIS -> new MyBatisFactory();
        };
    }
}

