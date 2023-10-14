package edu.hw2.Task3.Managers;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random RANDOM = new Random();

    @Override
    public Connection getConnection() {
        return RANDOM.nextInt(100) < 85
            ? new StableConnection()
            : new FaultyConnection();
    }
}
