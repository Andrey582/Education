package edu.hw2.Task3.Managers;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.Connections.FaultyConnection;
import java.util.Random;

public class FaultyConnectionManager implements ConnectionManager {
    private final Random RANDOM = new Random();
    private int attempts = 0;

    @Override
    public Connection getConnection() {
        attempts++;
        return new FaultyConnection(attempts);
    }
}
