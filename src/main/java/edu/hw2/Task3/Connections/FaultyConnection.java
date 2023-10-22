package edu.hw2.Task3.Connections;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int attemptsToGoodResult = 5;
    private int attempts = 0;

    @Override
    public void execute(String command) throws ConnectionException {
        if (attempts < attemptsToGoodResult) {
            throw new ConnectionException("Command failed",
                new Throwable("The faulty connection couldn't execute the command"));
        } else {
            LOGGER.info(command + ". Execute successfully");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("The connection was closed");
    }

    public FaultyConnection(int attempts) {
        this.attempts = attempts;
    }
}
