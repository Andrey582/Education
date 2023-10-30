package edu.hw2.Task3.Managers;

import edu.hw2.Task3.Connections.Connection;
import edu.hw2.Task3.Connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private int attempts = 0;

    @Override
    public Connection getConnection() {
        attempts++;
        return new FaultyConnection(attempts);
    }
}
