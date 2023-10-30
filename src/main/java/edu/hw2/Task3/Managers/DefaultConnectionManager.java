package edu.hw2.Task3.Managers;

import edu.hw2.Task3.Connections.Connection;
import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private final int attemptsToGoodResult = 5;
    private int attempts = 0;

    @Override
    public Connection getConnection() {
        attempts++;
        return attempts % attemptsToGoodResult != 0
            ? new FaultyConnection(attempts)
            : new StableConnection();
    }


}
