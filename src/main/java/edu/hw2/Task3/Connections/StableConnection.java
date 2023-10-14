package edu.hw2.Task3.Connections;

import edu.hw2.Task3.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info(command + ". Execute successfully");
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("The connection was closed");
    }
}
