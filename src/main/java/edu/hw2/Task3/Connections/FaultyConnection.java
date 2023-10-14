package edu.hw2.Task3.Connections;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

public class FaultyConnection implements Connection {
    private final Logger LOGGER = LogManager.getLogger();
    private final Random RANDOM = new Random();

    @Override
    public void execute(String command) throws ConnectionException {
        if (RANDOM.nextInt(100) < 85) {
            LOGGER.info(command + ". Execute successfully");
        } else {
            throw new ConnectionException("Command failed");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("The connection was closed");
    }
}
