package com.solvd.app.utils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
private static ConnectionPool instance = null;
    private static final int INITIAL_POOL_SIZE = 5;
    private static Vector<Connection> freeConnections = new Vector<>();
    private static Vector<Connection> usedConnections = new Vector<>();

    public ConnectionPool(int poolSize) {
        this.connectionPool = new ArrayList<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            connectionPool.add(createNewConnectionForPool());
        }
    }

    private Connection createNewConnectionForPool() {
        return DBConnection.getConnection();
    }

    public synchronized Connection getConnection() {
        int lastIndex = connectionPool.size() - 1;
        if (lastIndex < 0) {
            return createNewConnectionForPool();
        } else {
            return connectionPool.remove(lastIndex);
        }
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
}