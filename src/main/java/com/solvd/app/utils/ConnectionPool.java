package com.solvd.app.utils;

import java.sql.Connection;
import java.util.Vector;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private static final int INITIAL_POOL_SIZE = 5;
    private static Vector<Connection> freeConnections = new Vector<>();
    private static Vector<Connection> usedConnections = new Vector<>();

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            create();
        }
        return instance;
    }

    public static void create() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            freeConnections.add(createNewConnectionForPool());
        }
    }

    private static Connection createNewConnectionForPool() {
        return DBConnection.getConnection();
    }

    public synchronized Connection getConnection() {
        Connection connection = freeConnections.remove(freeConnections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        freeConnections.add(connection);
    }
}